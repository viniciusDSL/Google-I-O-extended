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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.controller.TalkController;
import com.vinidsl.googleioextended.model.DiaryItem;
import com.vinidsl.googleioextended.model.Talk;
import com.vinidsl.googleioextended.ui.adapter.DiaryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius on 11-06-15.
 */
public class DiaryFragment extends Fragment {

    private RecyclerView recyclerViewContacts;
    private RecyclerView.Adapter adapterContacts;
    private RecyclerView.LayoutManager layoutManager;

    private TextView textView404;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK){
            inflateData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diary, container, false);

        recyclerViewContacts = (RecyclerView) rootView.findViewById(R.id.recyclerViewDiary);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewContacts.setLayoutManager(layoutManager);

        textView404 = (TextView) rootView.findViewById(R.id.textView404);
        inflateData();
        return rootView;
    }

    private void inflateData(){
        recyclerViewContacts.setAdapter(null);
        List<Talk> customerList = TalkController.getDiaryTalks(getActivity());
        if(customerList == null || customerList.isEmpty()){
            textView404.setVisibility(View.VISIBLE);
        }else {
            textView404.setVisibility(View.INVISIBLE);
            List<DiaryItem> diaryItems = new ArrayList<>();
            DiaryItem diaryItem = null;
            int lastOrder=-1;
            for (Talk talk : customerList) {
                if(talk.getOrder()==lastOrder){
                    diaryItem.setTalk2(talk);
                }else{
                    if(diaryItem!=null){
                        diaryItems.add(diaryItem);
                    }
                    lastOrder = talk.getOrder();
                    diaryItem = new DiaryItem();
                    diaryItem.setTalk1(talk);
                }
            }
            if(diaryItem!=null){
                diaryItems.add(diaryItem);
            }
            adapterContacts = new DiaryAdapter(this, diaryItems);
            recyclerViewContacts.setAdapter(adapterContacts);
        }
    }
}
