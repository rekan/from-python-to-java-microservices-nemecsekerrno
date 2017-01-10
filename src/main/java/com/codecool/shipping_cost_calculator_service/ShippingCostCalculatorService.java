package com.codecool.shipping_cost_calculator_service;


import com.codecool.shipping_cost_calculator_service.controller.ShippingCostCalculatorAPIController;
import com.codecool.shipping_cost_calculator_service.service.ShippingCostCalculatorAPIService;

import static spark.Spark.*;

/**
 * Created by berloc on 2017.01.10..
 */
public class ShippingCostCalculatorService {

    private ShippingCostCalculatorAPIController controller;

    public static void main(String[] args) {
        port(65011);
        ShippingCostCalculatorService app = new ShippingCostCalculatorService();
        app.controller = new ShippingCostCalculatorAPIController(ShippingCostCalculatorAPIService.getINSTANCE());


    }
}


