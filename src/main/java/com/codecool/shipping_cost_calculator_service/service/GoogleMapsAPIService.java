package com.codecool.shipping_cost_calculator_service.service;

/**
 * Created by shevah on 10/01/17.
 */
public class GoogleMapsAPIService {
    private static final String GoogleAPIURL= "https://maps.googleapis.com/maps/api/distancematrix/json?";
    private static final String GoogleAPIKEy = "AIzaSyBLun5iVfffgdRpSLtqmbsyYMrBRYfpG78";
    private static GoogleMapsAPIService INSTANCE;

    public static GoogleMapsAPIService getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleMapsAPIService();
        }
        return INSTANCE;
    }

}
