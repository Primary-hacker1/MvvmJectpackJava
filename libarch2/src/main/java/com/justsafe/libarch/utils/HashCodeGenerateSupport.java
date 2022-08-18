package com.justsafe.libarch.utils;

public class HashCodeGenerateSupport {
    public static int hash(int... values) {
        int hash = 17;
        for (int value : values) {
            hash = hash * 31 + value;
        }
        return hash;
    }

    public static int deepHashCode(int value) {
        return value;
    }

    public static int deepHashCode(String value) {
        return value.hashCode();
    }

    public static int deepHashCode(float value) {
        return Float.floatToIntBits(value);
    }
}
