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
package com.vinidsl.googleioextended.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.vinidsl.googleioextended.model.GdgNotification;

/**
 * Created by Vinicius on 5/13/15.
 */
public class PushNotificationReceiver extends BroadcastReceiver {
    private PushNotificationListener pushNotificationListener;
    private IntentFilter intentFilter;


    public PushNotificationReceiver(PushNotificationListener pushNotificationListener){
        super();
        this.pushNotificationListener = pushNotificationListener;
        this.intentFilter = new IntentFilter("android.intent.action.MAIN");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        GdgNotification gdgNotification = new GdgNotification();
        Integer messageRes = intent.getIntExtra(GdgNotification.NOTIFICATION_SYNCHRO_RES, 0);
        String message = intent.getStringExtra(GdgNotification.NOTIFICATION_SYNCHRO);
        gdgNotification.setMessage(message);
        gdgNotification.setResourceMessage(messageRes);
        pushNotificationListener.onNotificationReceive(gdgNotification);
    }

    public void register(Activity activity){
        activity.registerReceiver(this,intentFilter);
    }

    public void unRegister(Activity activity){
        activity.unregisterReceiver(this);
        pushNotificationListener = null;
    }

    public interface PushNotificationListener {
        void onNotificationReceive(GdgNotification gdgNotification);
    }
}
