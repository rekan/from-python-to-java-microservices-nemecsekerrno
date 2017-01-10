package com.codecool.shipping_cost_calculator_service.controller;

import com.codecool.shipping_cost_calculator_service.service.GoogleMapsAPIService;
import spark.Request;
import spark.Response;

import java.util.HashMap;

/**
 * Created by shevah on 10/01/17.
 */
public class ShippingCostCalculatorAPIController {

    private final GoogleMapsAPIService apiService;

    public ShippingCostCalculatorAPIController( GoogleMapsAPIService apiService) {
        this.apiService = apiService;
    }

    public HashMap shippingCost(Request request, Response response) {
        return null;
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
