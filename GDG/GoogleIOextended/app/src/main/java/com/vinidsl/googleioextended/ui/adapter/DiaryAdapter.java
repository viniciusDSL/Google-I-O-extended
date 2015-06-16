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
package com.vinidsl.googleioextended.ui.adapter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.helper.IntentHelper;
import com.vinidsl.googleioextended.model.DiaryItem;
import com.vinidsl.googleioextended.model.Talk;

import java.util.List;

/**
 * Created by Vinicius on 10-06-15.
 */
public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolderDiary>{

    private Fragment fragment;
    private List<DiaryItem> diaryItemList;

    public DiaryAdapter(Fragment fragment, List<DiaryItem> diaryItemList){
        this.fragment =fragment;
        this.diaryItemList = diaryItemList;
    }

    @Override
    public ViewHolderDiary onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_diary, parent, false);
        ViewHolderDiary viewHolderDiary = new ViewHolderDiary(v);
        return viewHolderDiary;
    }

    @Override
    public void onBindViewHolder(ViewHolderDiary holder, int position) {

        DiaryItem diaryItem = diaryItemList.get(position);
        final Talk talk1 = diaryItem.getTalk1();
        final Talk talk2 = diaryItem.getTalk2();
        Resources resources = fragment.getActivity().getResources();

        if(talk2!=null){
            holder.textViewLocation1.setTextColor(resources.getColor(R.color.red));
            holder.textViewLocation2.setTextColor(resources.getColor(R.color.red));
        }else{
            holder.textViewLocation1.setTextColor(resources.getColor(R.color.secondary_text));
            holder.textViewLocation2.setTextColor(resources.getColor(R.color.secondary_text));
        }
        holder.textViewDate.setText(talk1.getDate());
        holder.textViewTitle1.setText(talk1.getTitle());
        holder.textViewLocation1.setText(talk1.getRoom());
        holder.linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.startDetailIntent(fragment,talk1.getId());
            }
        });
        if(talk2!=null){
            holder.linearLayout2.setVisibility(View.VISIBLE);
            holder.textViewTitle2.setText(talk2.getTitle());
            holder.textViewLocation2.setText(talk2.getRoom());
            holder.linearLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentHelper.startDetailIntent(fragment,talk2.getId());
                }
            });
        }else{
            holder.linearLayout2.setVisibility(View.GONE);
        }



        if(position==getItemCount()){
            holder.divider.setVisibility(View.INVISIBLE);
        }else{
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return diaryItemList.size();
    }

    public static class ViewHolderDiary extends RecyclerView.ViewHolder {

        public TextView textViewDate;
        public TextView textViewTitle1;
        public TextView textViewLocation1;
        public TextView textViewTitle2;
        public TextView textViewLocation2;
        public LinearLayout linearLayout1;
        public LinearLayout linearLayout2;
        public LinearLayout divider;

        public ViewHolderDiary(View v) {
            super(v);
            linearLayout1 = (LinearLayout)v.findViewById(R.id.linearLayout1);
            linearLayout2 = (LinearLayout)v.findViewById(R.id.linearLayout2);

            textViewTitle1 = (TextView)v.findViewById(R.id.textViewTitle1);
            textViewTitle2 = (TextView)v.findViewById(R.id.textViewTitle2);

            textViewLocation1 = (TextView)v.findViewById(R.id.textViewPlace1);
            textViewLocation2 = (TextView)v.findViewById(R.id.textViewPlace2);

            textViewDate = (TextView) v.findViewById(R.id.textViewDate);

            divider = (LinearLayout) v.findViewById(R.id.divider);
        }
    }
}
