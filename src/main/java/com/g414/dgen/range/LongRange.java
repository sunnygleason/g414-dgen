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

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Implements a Range of Long values. The range may be random, repeating, as
 * well as have a "step" value. P1 is, for the random case, the prime modulus.
 * P2 is, for the random case, a random offset.
 */
public class LongRange implements Range<Long> {
    protected final long start;
    protected final long stop;
    protected final long step;
    protected final BigInteger span;
    protected final BigInteger p1;
    protected final BigInteger p2;
    protected final boolean repeating;

    protected LongRange(long start, long stop, long step, boolean repeating,
            BigInteger p1, BigInteger p2) {
        if (stop <= start || stop - start < 1) {
            throw new IllegalArgumentException(
                    "bad idea! stop <= start or step < 1");
        }

        this.start = start;
        this.stop = stop;
        this.step = step;

        this.span = BigInteger.valueOf(stop - start);

        this.p1 = p1;
        this.p2 = p2;

        this.repeating = repeating;
    }

    @Override
    public long size() {
        return repeating ? Long.MAX_VALUE : span.divide(
                BigInteger.valueOf(this.step)).longValue();
    }

    @Override
    public Long get(long index) {
        if (this.p1.equals(BigInteger.ONE) && this.p2.equals(BigInteger.ZERO)) {
            return BigInteger.valueOf(index).multiply(BigInteger.valueOf(step))
                    .mod(span).add(BigInteger.valueOf(start)).longValue();
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            protected final long limit = span.longValue();
            protected BigInteger current = p2.mod(span);
            protected volatile long count = 0;

            @Override
            public boolean hasNext() {
                return repeating || count < limit;
            }

            @Override
            public synchronized Long next() {
                if (!hasNext()) {
                    throw new IllegalStateException("LongRange iterator has no next()");
                }
                
                BigInteger curSnap = current;

                for (long i = 0; i < step; i++) {
                    current = current.add(p1).mod(span);
                }

                count += step;

                return start + curSnap.longValue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
