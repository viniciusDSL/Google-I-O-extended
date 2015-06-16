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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Vinicius on 09-06-15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Speaker extends BaseModel {

    @JsonProperty
    @DatabaseField
    private String name;

    @JsonProperty
    @DatabaseField
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String url;

    @JsonProperty
    @DatabaseField
    private String biography;

    @JsonProperty
    @DatabaseField
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String twitter;

    @JsonProperty
    @DatabaseField
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String gplus;

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGplus() {
        return gplus;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
