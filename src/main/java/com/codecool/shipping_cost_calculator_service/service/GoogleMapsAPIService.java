package com.codecool.shipping_cost_calculator_service.service;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author      Errno Nemecsek <nemecsek_errno@bla.com>
 * @version     1.0
 * @since       1.0
 */

public class GoogleMapsAPIService {
    private static final String GoogleAPIURL= "https://maps.googleapis.com/maps/api/distancematrix/json";
    private static final String GoogleAPIKey = "AIzaSyBLun5iVfffgdRpSLtqmbsyYMrBRYfpG78";

    private static GoogleMapsAPIService INSTANCE;

    /**
     * Returns singleton instance of the class.
     *
     * @return      Singleton instance of the class.
     */
    public static GoogleMapsAPIService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleMapsAPIService();
        }
        return INSTANCE;
    }

    /**
     * Builds URL and executes the request.
     *
     * @param origin                Origin address.
     * @param destination           Destination address.
     * @return                      Response data as String.
     * @throws IOException
     * @throws URISyntaxException
     */
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
