/*
Copyright 2016 S7connector members (github.com/s7connector)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.github.s7connector.test.converter;

import com.github.s7connector.impl.serializer.converter.LRealConverter;
import com.github.s7connector.test.converter.base.ConverterBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class LRealConverterTest extends ConverterBase {
    @Test
    public void insert1() {
        LRealConverter c = new LRealConverter();
        byte[] buffer = new byte[8];
        c.insert(3.141f, buffer, 0, 0, 8);
        dump(buffer);
    }

    @Test
    public void loopTest1() {
        Random r = new Random();
        for (int i = 0; i < 1000; i++)
            loop(r.nextDouble() * r.nextInt(1000000));
    }

    public void loop(double f) {
        System.out.println("Testing: " + f);
        LRealConverter c = new LRealConverter();
        byte[] buffer = new byte[8];
        c.insert(f, buffer, 0, 0, 8);
        double ret = c.extract(Double.class, buffer, 0, 0);
        Assert.assertEquals(f, ret, 0.1);
    }

}
