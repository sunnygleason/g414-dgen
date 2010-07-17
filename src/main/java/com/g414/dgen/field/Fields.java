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
package com.g414.dgen.field;

import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

/**
 * Collection of useful Field implementations.
 */
public class Fields {
    /** Always returns the value of the entityId provided */
    public static Field<String> getIdField(final String name) {
        return new Field<String>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getValue(String entityId,
                    Map<String, Object> entitySoFar, Random random) {
                return entityId;
            }
        };
    }

    /** Always returns the constant provided */
    public static Field<Object> getConstantField(final String name,
            final Object value) {
        return new Field<Object>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public Object getValue(String entityId,
                    Map<String, Object> entitySoFar, Random random) {
                return value;
            }
        };
    }

    /** Returns a random boolean */
    public static Field<Boolean> getBooleanField(final String name) {
        return new Field<Boolean>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public Boolean getValue(String entityId,
                    Map<String, Object> entitySoFar, Random random) {
                return random.nextBoolean();
            }
        };
    }

    /** returns a random integer in the given range */
    public static Field<Integer> getIntField(final String name,
            final int start, final int stop) {
        return new Field<Integer>() {
            private final int range = stop - start;

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Integer getValue(String entityId,
                    Map<String, Object> entitySoFar, Random random) {
                int next = random.nextInt(range);

                return next + start;
            }
        };
    }

    /**
     * returns some random Hex bytes : note, length is of "original" binary
     * bytes
     */
    public static Field<String> getRandomHexBytesField(final String name,
            final int length) {
        return new Field<String>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getValue(String entityId,
                    Map<String, Object> entitySoFar, Random random) {
                byte[] next = new byte[length];
                random.nextBytes(next);

                return Hex.encodeHexString(next);
            }
        };
    }

    /** returns some random bytes */
    public static Field<byte[]> getRandomBinaryField(final String name,
            final int length) {
        return new Field<byte[]>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public byte[] getValue(String entityId,
                    Map<String, Object> entitySoFar, Random random) {
                byte[] next = new byte[length];
                random.nextBytes(next);

                return next;
            }
        };
    }
}
