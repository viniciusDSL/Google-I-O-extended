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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * /**
 * Created by Vinicius DSL on 12-01-15.
 * This class help to manage the shared preferences
 */
public class PreferencesHelper {

    public static final String FIRST_SYNC = "first_sync";
    public static final String IS_SYNC = "is_sync";
    public static final String ACCEPT_CONDITIONS = "accept_conditions";
    public static final String MAP = "map";
    private static String name = "GoogleIoExtended";
    public static final String NULL = "null";

    /**
     * Returns Editor to modify values of SharedPreferences
     * @param context Application context
     * @return
     */
    private static Editor getEditor(Context context){
        return getPreferences(context).edit();
    }

    /**
     * Returns SharedPreferences object
     * @param context Application context
     * @return
     */
    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
    }

    /**
     * Save a string on SharedPreferences
     * @param tag tag
     * @param value value
     * @param context Application context
     */
    public static void putString(String tag, String value, Context context) {
        Editor editor = getEditor(context);
        editor.putString(tag, value);
        editor.commit();
    }

    /**
     * Save an int on SharedPreferences
     * @param tag tag
     * @param value value
     * @param context Application context
     */
    public static void putInt(String tag, int value, Context context) {
        Editor editor = getEditor(context);
        editor.putInt(tag, value);
        editor.commit();
    }

    /**
     * Save a boolean on SharedPreferences
     * @param tag tag
     * @param value value
     * @param context Application context
     */
    public static void putBoolean(String tag, boolean value, Context context) {
        Editor editor = getEditor(context);
        editor.putBoolean(tag, value);
        editor.commit();
    }

    /**
     * Get a string value from SharedPreferences
     * @param tag tag
     * @param context Application context
     * @return
     */
    public static String getString(String tag, Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        return sharedPreferences.getString(tag, NULL);
    }

    /**
     * Get an int value from SharedPreferences
     * @param tag tag
     * @param context Application context
     * @return
     */
    public static int getInt(String tag, Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        return sharedPreferences.getInt(tag, 0);
    }

    /**
     * Get a boolean value from SharedPreferences
     * @param tag tag
     * @param context Application context
     * @return
     */
    public static boolean getBoolean(String tag, Context context) {
        SharedPreferences sharedPreferences = getPreferences(context);
        return sharedPreferences.getBoolean(tag, false);
    }

    /**
     * Clear all SharedPreference data
     * @param context Application context
     */
    public static void clear(Context context){
        Editor editor = getEditor(context);
        editor.clear();
        editor.commit();
    }

}

