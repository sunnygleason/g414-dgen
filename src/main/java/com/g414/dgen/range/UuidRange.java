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
package com.g414.dgen.range;

import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

/**
 * Provides a Range of UUID values based on a given LongRange instance.
 */
public class UuidRange implements Range<String> {
    protected final LongRange impl;

    public UuidRange(LongRange impl) {
        this.impl = impl;
    }

    @Override
    public long size() {
        return impl.size();
    }

    @Override
    public String get(long index) {
        return longToUuid(impl.get(index));
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            protected final Iterator<Long> under = impl.iterator();

            @Override
            public boolean hasNext() {
                return under.hasNext();
            }

            @Override
            public void remove() {
                under.remove();
            }

            @Override
            public String next() {
                return longToUuid(under.next());
            }
        };
    }

    protected static String longToUuid(long curSnap) {
        Random random = new Random(curSnap);
        return (new UUID(random.nextLong(), random.nextLong())).toString();
    }
}
