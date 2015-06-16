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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Vinicius on 09-06-15.
 */
public class BaseModel {

    public static final String OBJECT_ID = "objectId";

    @DatabaseField(id = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty
    protected String objectId;

    public void setId(String objectId) {
        this.objectId = objectId;
    }

    public String getId() {
        return objectId;
    }
}
