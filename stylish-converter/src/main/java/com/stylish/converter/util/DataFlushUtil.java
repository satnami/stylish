package com.stylish.converter.util;

import com.stylish.converter.cache.InMemory;

public class DataFlushUtil {

    public static void main(String[] args) {
        InMemory.getInstance().flush();
    }
}
