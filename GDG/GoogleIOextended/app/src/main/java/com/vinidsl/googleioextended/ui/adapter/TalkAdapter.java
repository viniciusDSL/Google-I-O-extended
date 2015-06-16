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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.helper.IntentHelper;
import com.vinidsl.googleioextended.model.Talk;

import java.util.List;

/**
 * Created by Vinicius on 10-06-15.
 */
public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.ViewHolderTalk>{

    private List<Talk> talkList;
    private Context context;

    public TalkAdapter(Context context, List<Talk> talkList){
        this.context = context;
        this.talkList = talkList;
    }


    @Override
    public ViewHolderTalk onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_explore, parent, false);
        ViewHolderTalk viewHolderTalk = new ViewHolderTalk(v);
        return viewHolderTalk;
    }

    @Override
    public void onBindViewHolder(ViewHolderTalk holder, int position) {
        final Talk talk = talkList.get(position);
        holder.textViewTitle.setText(talk.getTitle());
        holder.textViewDate.setText(context.getString(R.string.day_date)+" \n"+talk.getDate());
        Glide.with(context).load(Talk.getRandomCheeseDrawable()).centerCrop().into(holder.imageViewLogo);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.startDetailIntent(context,talk.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return talkList.size();
    }

    public static class ViewHolderTalk extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public TextView textViewDate;
        public ImageView imageViewLogo;
        public LinearLayout linearLayout;

        public ViewHolderTalk(View v) {
            super(v);
            linearLayout = (LinearLayout)v;
            textViewTitle = (TextView) v.findViewById(R.id.titleExplore);
            textViewDate = (TextView) v.findViewById(R.id.dateExplore);
            imageViewLogo = (ImageView) v.findViewById(R.id.imageExplore);
        }
    }

}
