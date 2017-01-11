package com.codecool.shipping_cost_calculator_service.controller;

import com.codecool.shipping_cost_calculator_service.service.GoogleMapsAPIService;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shevah on 10/01/17.
 */
public class ShippingCostCalculatorController {

    private final GoogleMapsAPIService apiService;

    public ShippingCostCalculatorController(GoogleMapsAPIService apiService) {
        this.apiService = apiService;
    }

    public String status(Request request, Response response) {
        return "ok";
    }

//    public HashMap generateOptions(Request request, Response response) throws IOException, URISyntaxException {
//        String originAddress = request.queryParams("origin");
//        String destinationAddress = request.queryParams("destination");
//        String rawData = apiService.requestData(originAddress, destinationAddress);
//        return null;
//    }

    public JSONArray extractData(Request request, Response response) throws JSONException, IOException, URISyntaxException {
        String originAddress = request.queryParams("origin");
        String destinationAddress = request.queryParams("destination");
        String rawData = apiService.requestData(originAddress, destinationAddress);

        JSONObject data = new JSONObject(rawData);

//        JSONObject googleMapsJSON = new JSONObject(rawData);
//        JSONObject elements = googleMapsJSON.getJSONObject("rows").getJSONObject("elements");
//        HashMap<String, String> distAndTimeValues = new HashMap<>();
//
//        distAndTimeValues.put("dist", elements.getJSONObject("distance").getString("text"));
//        distAndTimeValues.put("time", elements.getJSONObject("time").getString("text"));

        return data.getJSONArray("rows");
    }
}