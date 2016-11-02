package com.stylish.converter.util;

import org.json.JSONObject;

public class ConversionUtil {

    public static float convert(String rates, String from, String target, float count) {
        JSONObject jsonObject = new JSONObject(rates);
        float fromCurrencyToTarget = Float.parseFloat(jsonObject.get(target).toString());
        return count * fromCurrencyToTarget;
    }
}