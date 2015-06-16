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
package com.vinidsl.googleioextended.model;

/**
 * Created by Vinicius on 09-06-15.
 */
public class GdgNotification {
    public static final String NOTIFICATION_TAG = "notify_tag";
    public static final String NOTIFICATION_SYNCHRO = "synchro_notify_message";
    public static final String NOTIFICATION_SYNCHRO_RES = "synchro_notify_resource";

    private Integer resourceMessage;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResourceMessage() {
        return resourceMessage;
    }

    public void setResourceMessage(Integer resourceMessage) {
        this.resourceMessage = resourceMessage;
    }
}
