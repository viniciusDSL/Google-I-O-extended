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

import android.content.Context;
import android.content.Intent;

import com.vinidsl.googleioextended.model.GdgNotification;

/**
 * Created by Vinicius on 5/13/15.
 */
public class PushNotificationSender {
    private GdgNotification  notification;

    public PushNotificationSender(GdgNotification notification){
        this.notification = notification;
    }

    public void pushNotification(Context context){
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.putExtra(GdgNotification.NOTIFICATION_TAG, 0);
        if(notification.getMessage()!=null) {
            intent.putExtra(GdgNotification.NOTIFICATION_SYNCHRO, notification.getMessage());
        }
        if(notification.getResourceMessage()!=null) {
            intent.putExtra(GdgNotification.NOTIFICATION_SYNCHRO_RES, notification.getResourceMessage());
        }
        context.sendBroadcast(intent);
    }
}
