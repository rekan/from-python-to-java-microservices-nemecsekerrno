package com.codecool.shipping_cost_calculator_service.controller;

import com.codecool.shipping_cost_calculator_service.model.ShippingOption;
import com.codecool.shipping_cost_calculator_service.service.GoogleMapsAPIService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * @author      Errno Nemecsek <nemecsek_errno@bla.com>
 * @version     1.0
 * @since       1.0
 */

public class ShippingCostCalculatorController {

    private final GoogleMapsAPIService apiService;

    /**
     * Initializes a newly created ShippingCostCalculatorController.
     *
     * @param apiService    API service used by controller.
     */
    public ShippingCostCalculatorController(GoogleMapsAPIService apiService) {
        this.apiService = apiService;
    }

    /**
     * Enables to check if service is running.
     *
     * @param request
     * @param response
     * @return          "OK" if service is running.
     */
    public String status(Request request, Response response) {
        return "ok";
    }

    /**
     * Generates shipping options between given from and to addresses.
     *
     * @param request
     * @param response
     * @return       JSON containing all shipping options generated.
     * @throws IOException
     * @throws URISyntaxException
     */
    public JSONObject generateOptions(Request request, Response response) throws IOException, URISyntaxException {
        String originAddress = request.queryParams("origin");
        String destinationAddress = request.queryParams("destination");

        if (originAddress.trim().isEmpty() || destinationAddress.trim().isEmpty()) {
            return generateErrorJson(response, 400, "Origin or destination cannot be empty or whitespaces only");
        }

        String rawData = apiService.requestData(originAddress, destinationAddress);
        JSONObject extractedData = extractData(response, rawData);

        if (extractedData.has("error")) {
            return extractedData;
        }

        JSONObject allOptionsJSON = new JSONObject();
        for (String type : ShippingOption.TYPES) {
            allOptionsJSON.put(type, (new JSONObject(new ShippingOption(type, extractedData.getString("originFound"),
                    extractedData.getString("destinationFound"), extractedData.getInt("dist"),
                    extractedData.getInt("time")))));
        }
        return allOptionsJSON;
    }

    private JSONObject extractData(Response res, String rawData) {
        JSONObject rawDataJSON = new JSONObject(rawData);
        String status = rawDataJSON.getString("status");
        JSONObject elements;
        String elementsStatus;

        switch (status){
            case "OVER_QUERY_LIMIT":
                return generateErrorJson(res, 429,
                        "The application has requested too many elements within the allowed time period." +
                                "The request should succeed if you try again after a reasonable amount of time.");
            case "REQUEST_DENIED":
                return generateErrorJson(res, 500,
                        "The service denied use of the Distance Matrix service by your web page.");
            case "UNKNOWN_ERROR":
                return generateErrorJson(res, 500,
                        "A Distance Matrix request could not be processed due to a server error." +
                                "The request may succeed if you try again.");
            case "OK":
                elements = rawDataJSON.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0);
                elementsStatus = elements.getString("status");
                break;
            default:
                return generateErrorJson(res, 501, "Distance Matrix status message not implemented");
        }

        switch (elementsStatus) {
            case "NOT_FOUND":
                return generateErrorJson(res, 400,
                        "The origin and/or destination of this pairing could not be geocoded.");
            case "ZERO_RESULTS":
                return generateErrorJson(res, 400, "No route could be found between the origin and destination.");
            case "OK":
                Integer rawDistance = elements.getJSONObject("distance").getInt("value");
                Integer rawTime = elements.getJSONObject("duration").getInt("value");
                HashMap<String, Object> optionValues = new HashMap<>();
                optionValues.put("dist", rawDistance/1000.f);
                optionValues.put("time", rawTime/3600.f);
                optionValues.put("originFound", rawDataJSON.getJSONArray("origin_addresses").get(0));
                optionValues.put("destinationFound", rawDataJSON.getJSONArray("destination_addresses").get(0));
                return new JSONObject(optionValues);
            default:
                return generateErrorJson(res, 501, "Distance Matrix elements status message not implemented");
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