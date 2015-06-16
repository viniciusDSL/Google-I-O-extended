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
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.broadcast.PushNotificationReceiver;
import com.vinidsl.googleioextended.helper.NetworkHelper;
import com.vinidsl.googleioextended.helper.PreferencesHelper;
import com.vinidsl.googleioextended.model.GdgNotification;
import com.vinidsl.googleioextended.service.SynchroService;
import com.vinidsl.googleioextended.ui.fragment.AboutFragment;
import com.vinidsl.googleioextended.ui.fragment.DiaryFragment;
import com.vinidsl.googleioextended.ui.fragment.ExploreFragment;
import com.vinidsl.googleioextended.ui.fragment.SocialFragment;

public class MainActivity extends AppCompatActivity implements PushNotificationReceiver.PushNotificationListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private PushNotificationReceiver pushNotificationReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!PreferencesHelper.getBoolean(PreferencesHelper.ACCEPT_CONDITIONS,this)){
            Intent intent = new Intent(this,WelcomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.main_titlee);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setupDrawerToggle();


        if(!PreferencesHelper.getBoolean(PreferencesHelper.IS_SYNC,this)){
            if(!PreferencesHelper.getBoolean(PreferencesHelper.FIRST_SYNC,this)){
                if(NetworkHelper.hasNetworkConnection(this)) {
                    Intent intent = new Intent(this, SynchroService.class);
                    startService(intent);
                }else{
                    Snackbar.make(toolbar,R.string.internet_error,Snackbar.LENGTH_LONG).show();
                }
            }
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }else {
            super.onBackPressed();
        }
    }

    private void setupDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setItemIconTintList(getResources().getColorStateList(R.color.tint_selector));
        navigationView.setItemTextColor(getResources().getColorStateList(R.color.tint_selector));
        navigationView.getMenu().findItem(R.id.nav_explore).setChecked(true);
        if(PreferencesHelper.getBoolean(PreferencesHelper.FIRST_SYNC, this)){
            changeFrameContent(new ExploreFragment());
        }
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if(PreferencesHelper.getBoolean(PreferencesHelper.FIRST_SYNC,MainActivity.this)) {
                            switch (menuItem.getItemId()) {
                                case R.id.nav_map:
                                    PreferencesHelper.putInt(PreferencesHelper.MAP,1,MainActivity.this);
                                    mDrawerLayout.closeDrawers();
                                    Intent intent = new Intent(MainActivity.this,MapActivity.class);
                                    startActivity(intent);
                                    return true;
                                case R.id.nav_diary:
                                    getSupportActionBar().setTitle(R.string.diary_title);
                                    changeFrameContent(new DiaryFragment());
                                    break;
                                case R.id.nav_explore:
                                    getSupportActionBar().setTitle(R.string.main_titlee);
                                    changeFrameContent(new ExploreFragment());
                                    break;
                                case R.id.nav_social:
                                    getSupportActionBar().setTitle(R.string.social_title);
                                    changeFrameContent(new SocialFragment());
                                    break;
                                case R.id.nav_about:
                                    getSupportActionBar().setTitle(R.string.about_title);
                                    changeFrameContent(new AboutFragment());
                                    break;
                            }
                            menuItem.setChecked(true);

                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pushNotificationReceiver = new PushNotificationReceiver(this);
        pushNotificationReceiver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pushNotificationReceiver.unRegister(this);
    }

    public void changeFrameContent(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    @Override
    public void onNotificationReceive(GdgNotification gdgNotification) {
        if(PreferencesHelper.getBoolean(PreferencesHelper.FIRST_SYNC,this)){
            changeFrameContent(new ExploreFragment());
        }
    }
}