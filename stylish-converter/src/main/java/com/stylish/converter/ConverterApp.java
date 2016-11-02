package com.stylish.converter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class ConverterApp {

    private static ConverterApp instance;
    public String redis_host;
    public String redis_key;
    public ArrayList<String> default_currencies = new ArrayList<>();

    private ConverterApp() {
        getProperties();
    }

    public static ConverterApp getInstance() {
        if (instance == null)
            instance = new ConverterApp();
        return instance;
    }

    private void getProperties() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("properties/converter.properties"));
            redis_host = props.getProperty("REDIS_HOST");
            redis_key = props.getProperty("REDIS_KEY");
            Collections.addAll(default_currencies, props.getProperty("DEFAULT_CURRENCIES").split(","));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}