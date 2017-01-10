package com.codecool.shipping_cost_calculator_service.service;

/**
 * Created by shevah on 10/01/17.
 */
public class ShippingCostCalculatorAPIService {
    private static final String URL= "ide jon a link";
    private static ShippingCostCalculatorAPIService INSTANCE;

    public static ShippingCostCalculatorAPIService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ShippingCostCalculatorAPIService();
        }
        return INSTANCE;
    }

}
