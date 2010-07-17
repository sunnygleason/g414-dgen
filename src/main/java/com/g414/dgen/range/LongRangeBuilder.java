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
import java.util.Random;

/**
 * Builder class for working with LongRange instances.
 */
public class LongRangeBuilder {
    protected long start = 0;
    protected long stop = 0;
    protected long step = 1;
    protected long seed = 0;

    protected boolean repeating = false;
    protected Random random = null;

    protected BigInteger p1 = null;
    protected BigInteger p2 = null;

    public LongRangeBuilder(long start, long stop) {
        this.start = start;
        this.stop = stop;
    }

    public LongRangeBuilder withStep(long step) {
        this.step = step;
        return this;
    }

    public LongRangeBuilder withSeed(long seed) {
        this.seed = seed;
        return this;
    }

    public LongRangeBuilder withRandom(Random random) {
        this.random = random;
        return this;
    }

    public LongRangeBuilder withP1(BigInteger p1) {
        this.p1 = p1;
        return this;
    }

    public LongRangeBuilder withP2(BigInteger p2) {
        this.p2 = p2;
        return this;
    }

    public LongRangeBuilder setRepeating(boolean repeating) {
        this.repeating = repeating;
        return this;
    }

    public LongRange build() {
        BigInteger theP1 = p1;
        if (theP1 == null) {
            if (random != null) {
                theP1 = BigInteger.probablePrime(128, random);
            } else {
                theP1 = BigInteger.ONE;
            }
        }

        BigInteger theP2 = p2;
        if (theP2 == null) {
            if (random != null) {
                theP2 = BigInteger.valueOf(Math.abs(random.nextLong()));
            } else {
                theP2 = BigInteger.ZERO;
            }
        }

        return new LongRange(start, stop, step, repeating, theP1, theP2);
    }
}
