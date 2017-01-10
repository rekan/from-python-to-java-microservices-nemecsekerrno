package com.codecool.shipping_cost_calculator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by shevah on 10/01/17.
 */
public class GoogleMapsAPIService {
    private static final String GoogleAPIURL= "https://maps.googleapis.com/maps/api/distancematrix/json";
    private static final String GoogleAPIKey = "AIzaSyBLun5iVfffgdRpSLtqmbsyYMrBRYfpG78";
    private static GoogleMapsAPIService INSTANCE;

    public static GoogleMapsAPIService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleMapsAPIService();
        }
        return INSTANCE;
    }

    public String requestData(String origin, String destination) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(GoogleAPIURL).addParameter("origins", origin)
                .addParameter("destinations", destination).addParameter("key", GoogleAPIKey);
        return execute(builder.build());
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }

}
