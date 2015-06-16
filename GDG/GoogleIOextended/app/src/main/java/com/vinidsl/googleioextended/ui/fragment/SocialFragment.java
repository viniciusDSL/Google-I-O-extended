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
package com.vinidsl.googleioextended.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.helper.IntentHelper;

/**
 * Created by Vinicius on 11-06-15.
 */
public class SocialFragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_social, container, false);

        ImageView imageViewBig = (ImageView)rootView.findViewById(R.id.imageViewBig);
        ImageView imageViewGplus = (ImageView)rootView.findViewById(R.id.gplus);
        ImageView imageViewTwitter= (ImageView)rootView.findViewById(R.id.twitter);

        imageViewBig.setOnClickListener(this);
        imageViewGplus.setOnClickListener(this);
        imageViewTwitter.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageViewBig:
                IntentHelper.goToUrlIntent(getActivity(),"https://www.facebook.com/GDG.bo?fref=ts");
                break;
            case R.id.gplus:
                IntentHelper.goToUrlIntent(getActivity(),"https://plus.google.com/u/0/+GDGBolivia/posts");
                break;
            case R.id.twitter:
                IntentHelper.goToUrlIntent(getActivity(),"https://twitter.com/GDGLaPaz");
                break;
        }
    }
}
