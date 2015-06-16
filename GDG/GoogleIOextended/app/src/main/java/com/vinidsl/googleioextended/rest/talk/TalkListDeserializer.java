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
package com.vinidsl.googleioextended.rest.talk;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinidsl.googleioextended.model.Talk;
import com.vinidsl.googleioextended.rest.WrappedError;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Vinicius on 09-06-15.
 */
public class TalkListDeserializer extends JsonDeserializer<TalkListResponse> {
    @Override
    public TalkListResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        TalkListResponse responseData = new TalkListResponse();
        if (node.has("results")) {
            String nodeAsString = node.get("results").toString();
            ArrayList<Talk> talks = new ObjectMapper().readValue(nodeAsString , new TypeReference<ArrayList<Talk>>() {
            });
            responseData.setTalkList(talks);
        } else {
            responseData.setResponseError(new WrappedError(new Exception("Error while deserializing response result")));
        }
        return responseData;
    }
}
