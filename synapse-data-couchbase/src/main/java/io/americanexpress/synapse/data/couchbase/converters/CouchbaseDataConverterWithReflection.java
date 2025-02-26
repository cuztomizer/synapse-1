/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.data.couchbase.converters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * The type Couchbase data converter with reflection.
 *
 * @param <T> the type parameter
 */
public class CouchbaseDataConverterWithReflection<T> implements DataConverterWithReflection<T> {

    private final ObjectMapper objectMapper;

    /**
     * Instantiates a new Couchbase data converter with reflection.
     *
     * @param objectMapper the object mapper
     */
    public CouchbaseDataConverterWithReflection(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public T apply(HashMap hashMap, Class<T> clazz) {
        LinkedHashMap data = (LinkedHashMap) hashMap.get("data");

        T obj = objectMapper.convertValue(data, clazz);
        setId(obj, hashMap, clazz);

        return obj;
    }
}
