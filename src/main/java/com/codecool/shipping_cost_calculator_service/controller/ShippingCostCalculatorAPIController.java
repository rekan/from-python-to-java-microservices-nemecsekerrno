package com.codecool.shipping_cost_calculator_service.controller;

import com.codecool.shipping_cost_calculator_service.service.ShippingCostCalculatorAPIService;
import spark.Request;
import spark.Response;

import java.util.HashMap;

/**
 * Created by shevah on 10/01/17.
 */
public class ShippingCostCalculatorAPIController {

    private final ShippingCostCalculatorAPIService apiService;

    public ShippingCostCalculatorAPIController( ShippingCostCalculatorAPIService apiService) {
        this.apiService = apiService;
    }

    public HashMap shippingCost(Request request, Response response) {
        return null;
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
