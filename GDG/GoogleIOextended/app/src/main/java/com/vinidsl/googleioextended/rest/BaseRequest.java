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
package com.vinidsl.googleioextended.rest;

import android.content.Context;

import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vinicius on 4/27/15.
 * Base class of all requests
 * @param <T> Object type return by the request;
 */
public abstract class BaseRequest<T> {

    private Context context;

    public BaseRequest(Context context){
        this.context = context;
    }

    /**
     * Gets the url used for the request
     * @return the url that will be used in the request.
     */
    public abstract String getUrl();

    /**
     * Returns the HttpHeaders to be used in the request.
     * @return
     */
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("X-Parse-Application-Id", "eOZ0eFf8g2uJuX2xom30uzsTPXjdGc4kvBUXsG7K");
        headers.add("X-Parse-REST-API-Key", "Qi8G0MAn090msWUuEchhKtIPzh0tPF8FlxKDRWXJ");
        return headers;
    }

    /**
     * Method that creates the restTemplate instance
     * @return a restTemplate instance to be used for the request.
     */
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    /**
     * Method that performs the requests
     * @return The Object obtained after performing the Request
     */
    public abstract T performRequest();

}
