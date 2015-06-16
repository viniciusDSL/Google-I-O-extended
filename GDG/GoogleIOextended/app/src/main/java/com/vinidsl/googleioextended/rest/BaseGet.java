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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius on 4/27/15.
 */
public abstract class BaseGet <T> extends BaseRequest<T> {

    public BaseGet(Context context) {
        super(context);
    }

    /**
     * Gets the Response class
     * @return the Response class
     */
    public abstract Class<T> getResponseClass();

    @Override
    public T performRequest() {
        HttpEntity<T> requestEntity = new HttpEntity<>(getHeaders());
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<T> requestResult = restTemplate.exchange(getUrl(), HttpMethod.GET, requestEntity, getResponseClass());
        T result = requestResult.getBody();
        return result;
    }
}
