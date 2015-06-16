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
package com.vinidsl.googleioextended.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.controller.TalkController;
import com.vinidsl.googleioextended.helper.IntentHelper;
import com.vinidsl.googleioextended.helper.PreferencesHelper;
import com.vinidsl.googleioextended.helper.TintHelper;
import com.vinidsl.googleioextended.model.Talk;
import com.vinidsl.googleioextended.ui.custom.TintIcon;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    public static final int TALK_RESULT = 123;

    private Toolbar toolbar;

    private Talk talk;
    private FloatingActionButton fab;
    private boolean updated;

    private TintIcon twitter;
    private TintIcon gplus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String id = getIntent().getExtras().getString(Talk.OBJECT_ID);
        talk = TalkController.getTalkById(this,id);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(Talk.getRandomCheeseDrawable()).centerCrop().into(imageView);

        CircleImageView avatar = (CircleImageView) findViewById(R.id.imageViewAvatar);
        if(talk.getSpeaker().getUrl()!=null) {
            Glide.with(this).load(talk.getSpeaker().getUrl()).centerCrop().into(avatar);
        }

        twitter = (TintIcon) findViewById(R.id.twitter);
        gplus = (TintIcon) findViewById(R.id.gplus);

        if(talk.getSpeaker().getTwitter()!=null){
            twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentHelper.goToUrlIntent(DetailActivity.this,talk.getSpeaker().getTwitter());
                }
            });
        }else{
            twitter.setVisibility(View.GONE);
        }
        if(talk.getSpeaker().getGplus()!=null){
            gplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentHelper.goToUrlIntent(DetailActivity.this,talk.getSpeaker().getGplus());
                }
            });
        }else{
            gplus.setVisibility(View.GONE);
        }

        ((TextView)findViewById(R.id.textViewTitle)).setText(talk.getTitle());
        ((TextView)findViewById(R.id.textViewDescription)).setText(talk.getDescription());
        ((TextView)findViewById(R.id.textViewSpeakerName)).setText(talk.getSpeaker().getName());
        ((TextView)findViewById(R.id.textViewDate)).setText(getString(R.string.day_date)+talk.getDate());
        ((TextView)findViewById(R.id.textViewSpeakerBiography)).setText(talk.getSpeaker().getBiography());


        fab = (FloatingActionButton) findViewById(R.id.fabButton);


        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(talk.isScheduled()){
                    disable();
                }else{
                    enable();
                }
            }
        });

        updated = talk.isScheduled();

        if(talk.isScheduled()){
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.check));
            fab.setBackgroundTintList(getResources().getColorStateList(R.color.fab_checked_normal));
        }
    }

    private void enable(){
        talk.setScheduled(true);
        TalkController.updateTalk(this, talk);
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.check));
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.fab_checked_normal));
    }

    private void disable(){
        talk.setScheduled(false);
        TalkController.updateTalk(this, talk);
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.plus));
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.fab_checked_list));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        toolbar.inflateMenu(R.menu.menu_detail);
        TintHelper.tintToolbarIcon(toolbar, R.id.action_map, getResources().getColor(R.color.primary_text_toolbar));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id ) {
            case android.R.id.home:
                if(updated!=talk.isScheduled()){
                    finishWithChanges();
                }else{
                    finish();
                }
                return true;
            case R.id.action_map:
                PreferencesHelper.putInt(PreferencesHelper.MAP,talk.getPlace(),this);
                Intent intent = new Intent(this,MapActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(updated!=talk.isScheduled()){
            finishWithChanges();
        }else{
            super.onBackPressed();
        }

    }

    private void finishWithChanges(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
