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

import org.springframework.http.HttpStatus;

/**
 * Created by Vinicius on 4/27/15.
 */
public class BaseResponse {
    private WrappedError responseError;
    private HttpStatus httpStatus;

    /**
     * Gets if the response has an error
     *
     */
    public boolean hasError() {
        return responseError != null;
    }

    /**
     * Gets an error returned in the response
     *
     */
    public WrappedError getResponseError() {
        return responseError;
    }

    /**
     * Sets an error returned in the response
     * @param responseError
     */
    public void setResponseError(WrappedError responseError) {
        this.responseError = responseError;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
