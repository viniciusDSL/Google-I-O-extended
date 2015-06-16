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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.controller.TalkController;
import com.vinidsl.googleioextended.helper.IntentHelper;
import com.vinidsl.googleioextended.model.Talk;
import com.vinidsl.googleioextended.ui.TalksActivity;

import java.util.List;

/**
 * Created by Vinicius on 06-06-15.
 */
public class ExploreFragment extends Fragment implements View.OnClickListener{

    private Talk talk1;
    private Talk talk2;
    private Talk talk3;
    private Talk talk4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);

        LinearLayout l1 = (LinearLayout) rootView.findViewById(R.id.explore1);
        LinearLayout l2 = (LinearLayout) rootView.findViewById(R.id.explore2);
        LinearLayout l3 = (LinearLayout) rootView.findViewById(R.id.explore3);
        LinearLayout l4 = (LinearLayout) rootView.findViewById(R.id.explore4);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);

        List<Talk> talkList = TalkController.getLocalTalks(getActivity(),4);

        talk1 = talkList.remove(0);
        talk2 = talkList.remove(0);
        talk3 = talkList.remove(0);
        talk4 = talkList.remove(0);


        setData(l1,talk1,R.drawable.material1);
        setData(l2,talk2,R.drawable.material2);
        setData(l3,talk3,R.drawable.material3);
        setData(l4, talk4, R.drawable.material4);



        rootView.findViewById(R.id.more).setOnClickListener(this);
        rootView.findViewById(R.id.card_view).setOnClickListener(this);

        return rootView;
    }

    private void setData(LinearLayout linearLayout, Talk talk, int resource){
        ((TextView) linearLayout.findViewById(R.id.titleExplore)).setText(talk.getTitle());
        ((TextView) linearLayout.findViewById(R.id.dateExplore)).setText(getActivity().getString(R.string.day_date)+" \n"+talk.getDate());
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.imageExplore);
        Glide.with(getActivity()).load(resource).centerCrop().into(imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.explore1:
                IntentHelper.startDetailIntent(this,talk1.getId());
                break;
            case R.id.explore2:
                IntentHelper.startDetailIntent(this,talk2.getId());
                break;
            case R.id.explore3:
                IntentHelper.startDetailIntent(this,talk3.getId());
                break;
            case R.id.explore4:
                IntentHelper.startDetailIntent(this,talk4.getId());
                break;
            case R.id.more:
                Intent intent = new Intent(getActivity(), TalksActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.card_view:
                IntentHelper.goToUrlIntent(getActivity(),"https://www.youtube.com/watch?v=7V-fIGMDsmE");
                break;
        }
    }
}
