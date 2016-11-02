package com.stylish.converter.api;

import com.stylish.converter.ConverterApp;
import com.stylish.converter.service.ExchangeRates;
import com.stylish.converter.util.ConversionUtil;

import java.util.Objects;

import static spark.Spark.get;

public class Main {

    private static void initializeRoutes() {

        get("/", (request, response) -> "Welcome to the currency converter.");

        get("/:currency", (request, response) -> {
            String currency = request.params(":currency");
            return ExchangeRates.get(currency);
        });

        get("/:from/:target/:count", (request, response) -> {
            String from = request.params(":from");
            String target = request.params(":target");
            float count = Float.parseFloat(request.params(":count"));
            if (Objects.equals(from, target))
                return count;

            String rates = ExchangeRates.get(from);
            return ConversionUtil.convert(rates, from, target, count);
        });
    }

    private static void initializeDefaultCurrencies() {
        ConverterApp.getInstance().default_currencies.forEach(ExchangeRates::fetchAndSave);
    }

    public static void main(String[] args) {
        initializeDefaultCurrencies();
        initializeRoutes();
    }
}
