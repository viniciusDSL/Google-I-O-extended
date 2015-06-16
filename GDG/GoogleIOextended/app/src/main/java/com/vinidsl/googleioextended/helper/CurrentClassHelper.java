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
package com.vinidsl.googleioextended.helper;

import android.app.ActivityManager;
import android.content.Context;
import com.vinidsl.googleioextended.ui.MainActivity;

import java.util.List;

/**
 * Created by Vinicius on 5/13/15.
 */
public class CurrentClassHelper {

    public static final int CURRENT_VIEW_TYPE_SYNCHRO = 0;

    private static final String MainActivityClass = MainActivity.class.toString().substring(6,MainActivity.class.toString().length());

    //get the current activity that is showing
    public static int getCurrentView(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        //TODO find alternative for deprecated method
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
        String className = taskInfo.get(0).topActivity.getClassName();
        if (className.equals(MainActivityClass)) {
            return CURRENT_VIEW_TYPE_SYNCHRO;
        }
        return -1;
    }
}
