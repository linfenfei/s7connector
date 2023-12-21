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
package com.github.s7connector.impl.serializer.converter;

import com.github.s7connector.api.S7Serializable;
import com.github.s7connector.api.S7Type;

public final class LRealConverter implements S7Serializable {

    private static final int OFFSET_POS1 = 0;
    private static final int OFFSET_POS2 = 1;
    private static final int OFFSET_POS3 = 2;
    private static final int OFFSET_POS4 = 3;

    private static final int OFFSET_POS5 = 4;

    private static final int OFFSET_POS6 = 5;

    private static final int OFFSET_POS7 = 6;

    private static final int OFFSET_POS8 = 7;

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T extract(final Class<T> targetClass, final byte[] buffer, final int byteOffset, final int bitOffset) {
        final Double dValue = Double.longBitsToDouble(
                  ((long) ((buffer[byteOffset+ OFFSET_POS1] & 0xff)) << 56)
                | ((long) ((buffer[byteOffset + OFFSET_POS2] & 0xff)) << 48)
                | ((long) ((buffer[byteOffset + OFFSET_POS3] & 0xff)) << 40)
                | ((long) ((buffer[byteOffset + OFFSET_POS4] & 0xff)) << 32)
                | ((long) ((buffer[byteOffset + OFFSET_POS5] & 0xff)) << 24)
                | ((long) ((buffer[byteOffset + OFFSET_POS6] & 0xff)) << 16)
                | ((long) ((buffer[byteOffset + OFFSET_POS7] & 0xff)) << 8)
                | ((buffer[byteOffset + OFFSET_POS8] & 0xff)));
        Object ret = dValue;
        if (targetClass == Float.class) {
            ret = Float.parseFloat(dValue.toString());
        }
        return targetClass.cast(ret);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public S7Type getS7Type() {
        return S7Type.LREAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSizeInBits() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSizeInBytes() {
        return 4;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(final Object javaType, final byte[] buffer, final int byteOffset, final int bitOffset, final int size) {
        final double dValue = Double.parseDouble(javaType.toString());
        final long lValue = Double.doubleToLongBits(dValue);
        buffer[byteOffset + OFFSET_POS1] = (byte) ((lValue >> 56) & 0xFF);
        buffer[byteOffset + OFFSET_POS2] = (byte) ((lValue >> 48) & 0xFF);
        buffer[byteOffset + OFFSET_POS3] = (byte) ((lValue >> 40) & 0xFF);
        buffer[byteOffset + OFFSET_POS4] = (byte) ((lValue >> 32) & 0xFF);
        buffer[byteOffset + OFFSET_POS5] = (byte) ((lValue >> 24) & 0xFF);
        buffer[byteOffset + OFFSET_POS6] = (byte) ((lValue >> 16) & 0xFF);
        buffer[byteOffset + OFFSET_POS7] = (byte) ((lValue >> 8) & 0xFF);
        buffer[byteOffset + OFFSET_POS8] = (byte) (lValue & 0xFF);
    }
}
