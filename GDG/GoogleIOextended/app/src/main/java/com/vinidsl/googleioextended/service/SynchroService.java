/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vinidsl.googleioextended.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.vinidsl.googleioextended.broadcast.PushNotificationSender;
import com.vinidsl.googleioextended.helper.CurrentClassHelper;
import com.vinidsl.googleioextended.helper.DataBaseHelper;
import com.vinidsl.googleioextended.helper.PreferencesHelper;
import com.vinidsl.googleioextended.model.GdgNotification;
import com.vinidsl.googleioextended.model.Talk;
import com.vinidsl.googleioextended.rest.WrappedError;
import com.vinidsl.googleioextended.rest.talk.TalkListRequest;
import com.vinidsl.googleioextended.rest.talk.TalkListResponse;

import org.springframework.web.client.HttpClientErrorException;

import java.sql.SQLException;

public class SynchroService extends Service {

    public SynchroService() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new SyncTask().execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class SyncTask extends AsyncTask<Void,Void,TalkListResponse> {

        private DataBaseHelper dataBaseHelper;

        private DataBaseHelper getHelper(){
            if (this.dataBaseHelper == null) {
                this.dataBaseHelper = OpenHelperManager.getHelper(SynchroService.this, DataBaseHelper.class);
            }
            return this.dataBaseHelper;
        }

        @Override
        protected TalkListResponse doInBackground(Void... params) {
            PreferencesHelper.putBoolean(PreferencesHelper.IS_SYNC,true,SynchroService.this);
            TalkListResponse response = new TalkListResponse();
            try{
                TalkListRequest request = new TalkListRequest(SynchroService.this);
                response = request.performRequest();
                storeTalks(response);
            }catch (HttpClientErrorException httpClientException) {
                response.setResponseError(new WrappedError(httpClientException.getStatusCode().value(), httpClientException));
            }catch (Exception e){
                response.setResponseError(new WrappedError(e));
            }
            return response;
        }

        @Override
        protected void onPostExecute(TalkListResponse response) {
            super.onPostExecute(response);
            if(response.hasError()){
                System.out.println("error "+response.getResponseError().getThrowable());
            }else{
                PreferencesHelper.putBoolean(PreferencesHelper.FIRST_SYNC, true, SynchroService.this);
                if (CurrentClassHelper.getCurrentView(SynchroService.this) == CurrentClassHelper.CURRENT_VIEW_TYPE_SYNCHRO) {
                    GdgNotification notification = new GdgNotification();
                    notification.setMessage("complete");
                    PushNotificationSender pushNotificationSender = new PushNotificationSender(notification);
                    pushNotificationSender.pushNotification(SynchroService.this);
                }
            }
            PreferencesHelper.putBoolean(PreferencesHelper.IS_SYNC,false,SynchroService.this);
        }

        private void storeTalks(TalkListResponse response){
            if(!response.hasError()){
                for(Talk talk:response.getTalkList()){
                    try {
                        talk.setScheduled(false);
                        getHelper().getSpeakerDao().createOrUpdate(talk.getSpeaker());
                        getHelper().getTalkDao().createOrUpdate(talk);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
