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
package com.vinidsl.googleioextended.model;

/**
 * Created by Vinicius on 11-06-15.
 */
public class DiaryItem {

    private Talk talk1;
    private Talk talk2;

    public Talk getTalk1() {
        return talk1;
    }

    public void setTalk1(Talk talk1) {
        this.talk1 = talk1;
    }

    public Talk getTalk2() {
        return talk2;
    }

    public void setTalk2(Talk talk2) {
        this.talk2 = talk2;
    }
}
