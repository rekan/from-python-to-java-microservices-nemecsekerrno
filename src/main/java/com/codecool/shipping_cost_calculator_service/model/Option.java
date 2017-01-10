package com.codecool.shipping_cost_calculator_service.model;

import java.util.Currency;

public class Option {
    private float distanceInKm;
    private float timeInHours;
    private String details;
    private float cost;
    private final Currency currency = Currency.getInstance("USD");

//    ------ PRICING CONSTANTS ------
    private final int StandardFeePerKm = 5;
    private final float HighwayDistanceModifier = 0.88f;
    private final float HighwayTimeModifier = 0.6f;
    private final int HighwayFeePerKm = 1;
    private final int ExpressCourierTime = distanceInKm < 400 ? 48 : 96;
    private final int ExpressCourierCost = distanceInKm < 400 ? 60 : 80;
    private final int TimeMachineTime = 1;
    private final int TimeMachineCost = 6000000;
//    -------------------------------
    

    public Option(String type, int distanceInKm, float timeInHours) {

        switch (type) {
            case "truck":
                this.distanceInKm = distanceInKm;
                this.timeInHours = timeInHours;
                this.details = "Standard truck avoiding highways";
                this.cost = distanceInKm * StandardFeePerKm;
                break;
            case "truckViaHighway":
                this.distanceInKm = distanceInKm * HighwayDistanceModifier;
                this.timeInHours = timeInHours * HighwayTimeModifier;
                this.details = "Standard truck via highway";
                this.cost = (distanceInKm * StandardFeePerKm)*(distanceInKm * HighwayFeePerKm);
                break;
            case "expressCourier":
                this.distanceInKm = distanceInKm;
                this.timeInHours = ExpressCourierTime;
                this.details = "Express courier with fixed cost and time";
                this.cost = ExpressCourierCost;
                break;
            case "timeMachine":
                this.distanceInKm = distanceInKm;
                this.timeInHours = TimeMachineTime;
                this.details = "Most advanced technology, totally safe - we promise";
                this.cost = TimeMachineCost;
                break;
            default:
                this.distanceInKm = distanceInKm;
                this.timeInHours = timeInHours;
                this.details = "Standard truck avoiding highways";
                this.cost = distanceInKm * StandardFeePerKm;
                break;
        }
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
