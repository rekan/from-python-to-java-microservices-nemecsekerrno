package com.codecool.shipping_cost_calculator_service.controller;

import com.codecool.shipping_cost_calculator_service.service.ShippingCostCalculatorAPIService;

/**
 * Created by shevah on 10/01/17.
 */
public class ShippingCostCalculatorAPIController {

    private final ShippingCostCalculatorAPIService apiService;

    public ShippingCostCalculatorAPIController( ShippingCostCalculatorAPIService apiService) {
        this.apiService = apiService;
    }

}
