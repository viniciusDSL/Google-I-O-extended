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

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;

import com.vinidsl.googleioextended.R;

/**
 * Created by Vinicius on 5/24/15.
 */
public class TintHelper {

    public static Drawable tint(Drawable drawable,int colorResource){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable,colorResource);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public static Drawable tint(Drawable drawable,ColorStateList stateList){
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(drawable, stateList);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public static void tintToolbarIcon(Toolbar toolbar, int menuItemId, int colorResource){
        Drawable drawable = toolbar.getMenu().findItem(menuItemId).getIcon();
        drawable = TintHelper.tint(drawable,colorResource);
        toolbar.getMenu().findItem(menuItemId).setIcon(drawable);
    }

}