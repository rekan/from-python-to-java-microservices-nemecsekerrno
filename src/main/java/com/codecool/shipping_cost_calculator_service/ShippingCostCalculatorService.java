package com.codecool.shipping_cost_calculator_service;


import com.codecool.shipping_cost_calculator_service.controller.ShippingCostCalculatorController;
import com.codecool.shipping_cost_calculator_service.service.GoogleMapsAPIService;

import java.net.URISyntaxException;

import static spark.Spark.*;

/**
 * Created by berloc on 2017.01.10..
 */
public class ShippingCostCalculatorService {

    private ShippingCostCalculatorController controller;

    public static void main(String[] args) {
        port(65011);
        ShippingCostCalculatorService app = new ShippingCostCalculatorService();
        app.controller = new ShippingCostCalculatorController(GoogleMapsAPIService.getINSTANCE());

        get("/status", app.controller::status);
        get("/shipping-cost", app.controller::extractData);

        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
        });
    }
}


