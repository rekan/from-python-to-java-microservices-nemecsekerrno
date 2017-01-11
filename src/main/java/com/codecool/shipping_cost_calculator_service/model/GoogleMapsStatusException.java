package com.codecool.shipping_cost_calculator_service.model;

/**
 * Created by hamargyuri on 2017. 01. 11..
 */
public class GoogleMapsStatusException extends Exception {
    GoogleMapsStatusException() {
        super();
    }
    public GoogleMapsStatusException(String message) {
        super(message);
    }
}
