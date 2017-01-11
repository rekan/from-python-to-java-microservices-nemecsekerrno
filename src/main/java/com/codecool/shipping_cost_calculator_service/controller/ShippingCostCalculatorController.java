package com.codecool.shipping_cost_calculator_service.controller;

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

    public JSONObject generateOptions(Request request, Response response) throws IOException, URISyntaxException {
        String originAddress = request.queryParams("origin");
        String destinationAddress = request.queryParams("destination");
        if (originAddress.trim().isEmpty() || destinationAddress.trim().isEmpty()) {
            return generateErrorJson(response, 400, "Origin or destination cannot be empty or whitespaces only");
        }
        String rawData = apiService.requestData(originAddress, destinationAddress);
        return extractData(response, rawData);
    }

    public JSONObject extractData(Response res, String rawData) throws JSONException {
        JSONObject rawDataJSON = new JSONObject(rawData);
        String status = rawDataJSON.getString("status");
        JSONObject elements;
        String elementsStatus;

        switch (status){
            case "OVER_QUERY_LIMIT":
                return generateErrorJson(res, 500,
                        "The application has requested too many elements within the allowed time period." +
                                "The request should succeed if you try again after a reasonable amount of time.");
            case "REQUEST_DENIED":
                return generateErrorJson(res, 500,
                        "The service denied use of the Distance Matrix service by your web page.");
            case "UNKNOWN_ERROR":
                return generateErrorJson(res, 500,
                        "A Distance Matrix request could not be processed due to a server error." +
                                "The request may succeed if you try again.");
            default:
                elements = rawDataJSON.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0);
                elementsStatus = elements.getString("status");
                break;
        }

        switch (elementsStatus) {
            case "NOT_FOUND":
                return generateErrorJson(res, 400,
                        "The origin and/or destination of this pairing could not be geocoded.");
            case "ZERO_RESULTS":
                return generateErrorJson(res, 400, "No route could be found between the origin and destination.");
            default:
                Integer rawDistance = elements.getJSONObject("distance").getInt("value");
                Integer rawTime = elements.getJSONObject("duration").getInt("value");
                HashMap<String, Float> distAndTimeValues = new HashMap<>();
                distAndTimeValues.put("dist", rawDistance/1000.f);
                distAndTimeValues.put("time", rawTime/3600.f);
                return new JSONObject(distAndTimeValues);
        }
    }


    private JSONObject generateErrorJson(Response response, int statusCode, String message) {
        response.status(statusCode);
        response.type("application/json");
        JSONObject errorJson = new JSONObject();
        errorJson.put("error", message);
        return errorJson;
    }
}