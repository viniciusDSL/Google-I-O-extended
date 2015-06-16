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
package com.vinidsl.googleioextended.controller;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.vinidsl.googleioextended.helper.DataBaseHelper;
import com.vinidsl.googleioextended.model.Talk;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vinicius on 09-06-15.
 */
public class TalkController {

    public static Talk getTalkById(Context context,String talkId){
        DataBaseHelper dataBaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        Talk result;
        try {
            result = dataBaseHelper.getTalkDao().queryForId(talkId);
        } catch (SQLException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public static void updateTalk(Context context,Talk talk){
        DataBaseHelper dataBaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        try {
            dataBaseHelper.getTalkDao().update(talk);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Talk> getAllTalks(Context context){
        DataBaseHelper dataBaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        try {
            QueryBuilder<Talk, String> builder = dataBaseHelper.getTalkDao().queryBuilder();
            builder.orderBy("order",true);
            List<Talk> talkList = dataBaseHelper.getTalkDao().query(builder.prepare());
            if(!talkList.isEmpty() && talkList!=null) {
                return talkList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Talk> getDiaryTalks(Context context){
        DataBaseHelper dataBaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        try {
            QueryBuilder<Talk, String> builder = dataBaseHelper.getTalkDao().queryBuilder();
            builder.orderBy("order",true);
            Where<Talk,String> where = builder.where();
            where.eq("scheduled", true);
            List<Talk> talkList = builder.query();
            if(!talkList.isEmpty() && talkList!=null) {
                return talkList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Talk> getLocalTalks(Context context, int size){
        DataBaseHelper dataBaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        try {
            QueryBuilder<Talk, String> builder = dataBaseHelper.getTalkDao().queryBuilder();
            builder.limit(size);
            List<Talk> talkList = dataBaseHelper.getTalkDao().query(builder.prepare());
            if(!talkList.isEmpty() && talkList!=null) {
                return talkList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
