package com.codecool.shipping_cost_calculator_service.model;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class ShippingOption {
    public static final List<String> TYPES = Arrays.asList("truck", "truckViaHighway", "expressCourier",
            "timeMachine");
    private String originFound;
    private String destinationFound;
    private float distanceInKm;
    private float timeInHours;
    private String details;
    private float cost;
    private final Currency currency = Currency.getInstance("USD");

//    ------ PRICING CONSTANTS ------
    private final float StandardFeePerKm = 0.01f;
    private final float HighwayDistanceModifier = 0.88f;
    private final float HighwayTimeModifier = 0.6f;
    private final int HighwayFeePerKm = 1;
    private final int ExpressCourierTime = distanceInKm < 400 ? 48 : 96;
    private final int ExpressCourierCost = distanceInKm < 400 ? 60 : 80;
    private final int TimeMachineTime = 1;
    private final int TimeMachineCost = 6000000;
//    -------------------------------


    public ShippingOption(String type, String originFound, String destinationFound, float distanceInKm, float timeInHours) {
        this.originFound = originFound;
        this.destinationFound = destinationFound;

        switch (type) {
            case "truck":
                this.distanceInKm = (int) Math.ceil(distanceInKm);
                this.timeInHours = (int) Math.ceil(timeInHours);
                this.details = "Standard truck avoiding highways";
                this.cost = (int) Math.ceil(distanceInKm * StandardFeePerKm);
                break;
            case "truckViaHighway":
                this.distanceInKm = (int) Math.ceil(distanceInKm * HighwayDistanceModifier);
                this.timeInHours = (int) Math.ceil(timeInHours * HighwayTimeModifier);
                this.details = "Standard truck via highway";
                this.cost = (int) Math.ceil((distanceInKm * StandardFeePerKm)+(distanceInKm * HighwayFeePerKm));
                break;
            case "expressCourier":
                this.distanceInKm = (int) Math.ceil(distanceInKm);
                this.timeInHours = ExpressCourierTime;
                this.details = "Express courier with fixed cost and time";
                this.cost = ExpressCourierCost;
                break;
            case "timeMachine":
                this.distanceInKm = (int) Math.ceil(distanceInKm);
                this.timeInHours = TimeMachineTime;
                this.details = "Most advanced technology, totally safe - we promise";
                this.cost = TimeMachineCost;
                break;
            default:
                this.distanceInKm = (int) Math.ceil(distanceInKm);
                this.timeInHours = (int) Math.ceil(timeInHours);
                this.details = "Standard truck avoiding highways";
                this.cost = (int) Math.ceil(distanceInKm * StandardFeePerKm);
                break;
        }
    }

    public String getOriginFound() {
        return originFound;
    }

    public String getDestinationFound() {
        return destinationFound;
    }

    public float getDistanceInKm() {
        return distanceInKm;
    }

    public float getTimeInHours() {
        return timeInHours;
    }

    public String getDetails() {
        return details;
    }

    public float getCost() {
        return cost;
    }

    public Currency getCurrency() {
        return currency;
    }
}
