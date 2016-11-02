package com.stylish.converter.service;

import com.stylish.converter.cache.InMemory;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class ExchangeRates {

    public static String get(final String base) {
        String rates = "";
        try {
            rates = InMemory.getInstance().get(base);
            if (rates == null) {
                rates = fetchAndSave(base);
                if (!Objects.equals(rates, "Wrong currency")) {
                    rates = InMemory.getInstance().get(base);
                }
            }
        } catch (Exception e) {
            rates = fetchAndSave(base);
        }
        return rates;
    }

    public static String fetchAndSave(final String base) {
        String response = fetchFromServer(base);
        if (response != null && !response.isEmpty() && !response.contains("error")) {
            JSONObject jsonObject = new JSONObject(response);
            InMemory.getInstance().set(base, jsonObject.get("rates").toString());
            return jsonObject.get("rates").toString();
        }
        return "Wrong currency";
    }

    private static String fetchFromServer(final String base) {
        String line = "";
        try {
            URL url;
            if (Objects.equals(base, ""))
                url = new URL("https://api.fixer.io/latest");
            else
                url = new URL("https://api.fixer.io/latest?base=" + base);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            line = reader.readLine();
            reader.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Getting: " + base + " from the server.");
        return line;
    }
}
