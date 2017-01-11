package com.codecool.shipping_cost_calculator_service.controller;

import com.codecool.shipping_cost_calculator_service.model.GoogleMapsStatusException;
import com.codecool.shipping_cost_calculator_service.service.GoogleMapsAPIService;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

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

    public JSONObject generateOptions(Request request, Response response) throws IOException,
            URISyntaxException,  GoogleMapsStatusException {
        String originAddress = request.queryParams("origin");
        String destinationAddress = request.queryParams("destination");
        if (originAddress.trim().isEmpty() || destinationAddress.trim().isEmpty()) {
            return generateErrorJson(response, 400, "Origin or destination cannot be empty or whitespaces only");
        }
        String rawData = apiService.requestData(originAddress, destinationAddress);
        JSONObject toReturn = new JSONObject(extractData(rawData));
        return toReturn;
    }


    public HashMap<String, Float> extractData(String rawData) throws JSONException, GoogleMapsStatusException {
        JSONObject rawDataJSON = new JSONObject(rawData);
        String status = rawDataJSON.getString("status");
        JSONObject elements = rawDataJSON.getJSONArray("rows").getJSONObject(0)
                .getJSONArray("elements").getJSONObject(0);
        if (!status.equals("OK")) {
            throw new GoogleMapsStatusException("GMaps status error: " + status);
        }

        Integer rawDistance = elements.getJSONObject("distance").getInt("value");
        Integer rawTime = elements.getJSONObject("duration").getInt("value");

        HashMap<String, Float> distAndTimeValues = new HashMap<>();
        distAndTimeValues.put("dist", rawDistance/1000.f);
        distAndTimeValues.put("time", rawTime/3600.f);

        return distAndTimeValues;
    }

    private JSONObject generateErrorJson(Response response, int statusCode, String message) {
        response.status(statusCode);
        response.type("application/json");
        JSONObject errorJson = new JSONObject();
        errorJson.put("error", message);
        return errorJson;
    }
}