/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.g414.dgen;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.g414.dgen.field.Field;
import com.g414.hash.LongHash;

/**
 * Provides a means for generating mass quantities of Entities, that is,
 * collections of key/value pairs representing a particular entity.
 */
public class EntityGenerator {
    protected final LongHash hash;
    protected final List<Field<?>> fields;
    protected final long seed;

    public EntityGenerator(LongHash hash, List<Field<?>> fields) {
        this(hash, fields, 0L);
    }

    public EntityGenerator(LongHash hash, List<Field<?>> fields, long seed) {
        this.hash = hash;
        this.fields = fields;
        this.seed = seed;
    }

    public Map<String, Object> getEntity(String id) {
        long entitySeed = hash.getLongHashCode(id) ^ this.seed;
        Random random = new Random(entitySeed);

        Map<String, Object> entity = new LinkedHashMap<String, Object>();
        for (Field<?> field : this.fields) {
            entity.put(field.getName(), field.getValue(id, entity, random));
        }

        return entity;
    }
}
