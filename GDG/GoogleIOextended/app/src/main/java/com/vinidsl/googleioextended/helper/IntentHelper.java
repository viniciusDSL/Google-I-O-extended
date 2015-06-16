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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.vinidsl.googleioextended.model.Talk;
import com.vinidsl.googleioextended.ui.DetailActivity;

/**
 * Created by Vinicius on 10-06-15.
 */
public class IntentHelper {

    public static void startDetailIntent(Fragment fragment,String talkId){
        Intent intent = new Intent(fragment.getActivity(), DetailActivity.class);
        intent.putExtra(Talk.OBJECT_ID,talkId);
        fragment.startActivityForResult(intent,DetailActivity.TALK_RESULT);
    }

    public static void startDetailIntent(Context context,String talkId){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Talk.OBJECT_ID,talkId);
        context.startActivity(intent);
    }

    public static void goToUrlIntent(Activity activity,String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }

}
