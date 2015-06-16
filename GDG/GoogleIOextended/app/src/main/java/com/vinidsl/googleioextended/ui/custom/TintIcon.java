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
package com.vinidsl.googleioextended.ui.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.vinidsl.googleioextended.R;
import com.vinidsl.googleioextended.helper.TintHelper;

/**
 * Created by Vinicius on 5/26/15.
 */
public class TintIcon extends ImageView {
    public TintIcon(Context context) {
        super(context);
    }

    public TintIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TintIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = getDrawable();
        drawable = TintHelper.tint(drawable, getResources().getColor(R.color.primary_text_toolbar));
        this.setImageDrawable(drawable);
    }
}
