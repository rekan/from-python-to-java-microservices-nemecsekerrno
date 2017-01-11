package com.codecool.shipping_cost_calculator_service.controller;

import com.codecool.shipping_cost_calculator_service.service.GoogleMapsAPIService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.HashMap;

/**
 * Created by shevah on 10/01/17.
 */
public class ShippingCostCalculatorController {

    private final GoogleMapsAPIService apiService;

    public ShippingCostCalculatorController(GoogleMapsAPIService apiService) {
        this.apiService = apiService;
    }

    public HashMap shippingCost(Request request, Response response) {
        return null;
    }

    public String status(Request request, Response response) {
        return "ok";
    }

    public HashMap<String, String> extractData(String googleMapsData) {
        JSONObject googleMapsJSON = new JSONObject(googleMapsData);
        JSONObject elements = googleMapsJSON.getJSONObject("rows").getJSONObject("elements");
        HashMap<String, String> distAndTimeValues = new HashMap<>();

        distAndTimeValues.put("dist", elements.getJSONObject("distance").getString("text"));
        distAndTimeValues.put("time", elements.getJSONObject("time").getString("text"));

        return distAndTimeValues;
    }
}