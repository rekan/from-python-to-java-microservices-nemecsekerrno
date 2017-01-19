package com.codecool.shipping_cost_calculator_service.model;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

/**
 * @author      Errno Nemecsek <nemecsek_errno@bla.com>
 * @version     1.0
 * @since       1.0
 */

public class ShippingOption {
    /**
     * All shipping option types generated by the service.
     */
    public static final List<String> TYPES = Arrays.asList("truck", "truckViaHighway", "expressCourier",
            "timeMachine");
    private String originFound;
    private String destinationFound;
    private int distanceInKm;
    private int timeInHours;
    private String details;
    private float cost;
    private final Currency currency = Currency.getInstance("USD");

//    ------ PRICING CONSTANTS ------
    private final float StandardFeePerKm = 0.01f;
    private final float HighwayDistanceModifier = 0.88f;
    private final float HighwayTimeModifier = 0.6f;
    private final float HighwayFeePerKm = 0.02f;
    private final int ExpressCourierTime = distanceInKm < 400 ? 48 : 96;
    private final int ExpressCourierCost = distanceInKm < 400 ? 5 : 10;
    private final int TimeMachineTime = 1;
    private final int TimeMachineCost = 6000000;
//    -------------------------------


    /**
     * Initializes a newly created ShippingOption object.
     *
     * @param type              Type of shipping option.
     * @param originFound       Origin address of shipping option.
     * @param destinationFound  Destination address of shipping option.
     * @param distanceInKm      Distance in KM between origin and destination addresses.
     * @param timeInHours       Transport time in HOURS between origin and destination addresses.
     */
    public ShippingOption(String type, String originFound, String destinationFound, float distanceInKm, float timeInHours) {
        this.originFound = originFound;
        this.destinationFound = destinationFound;
        int roundedTimeInHours = (int) Math.ceil(timeInHours) < 1 ? 1 : (int) Math.ceil(timeInHours);
        int roundedDistanceInKm = (int) Math.ceil(distanceInKm) < 1 ? 1 : (int) Math.ceil(distanceInKm);

        switch (type) {
            case "truck":
                this.distanceInKm = roundedDistanceInKm;
                this.timeInHours = roundedTimeInHours;
                this.details = "Standard truck avoiding highways";
                this.cost = (int) Math.ceil(roundedDistanceInKm * StandardFeePerKm);
                break;
            case "truckViaHighway":
                this.distanceInKm = (int) Math.ceil(roundedDistanceInKm * HighwayDistanceModifier);
                this.timeInHours = (int) Math.ceil(roundedTimeInHours * HighwayTimeModifier);
                this.details = "Standard truck via highway";
                this.cost = (int) Math.ceil(roundedDistanceInKm * HighwayFeePerKm);
                break;
            case "expressCourier":
                this.distanceInKm = roundedDistanceInKm;
                this.timeInHours = ExpressCourierTime;
                this.details = "Express courier with fixed cost and time";
                this.cost = ExpressCourierCost;
                break;
            case "timeMachine":
                this.distanceInKm = roundedDistanceInKm;
                this.timeInHours = TimeMachineTime;
                this.details = "Most advanced technology, totally safe - we promise";
                this.cost = TimeMachineCost;
                break;
            default:
                this.distanceInKm = roundedDistanceInKm;
                this.timeInHours = roundedTimeInHours;
                this.details = "Standard truck avoiding highways";
                this.cost = (int) Math.ceil(roundedDistanceInKm * StandardFeePerKm);
                break;
        }
    }

    /**
     * @return  The origin address.
     */
    public String getOriginFound() {
        return originFound;
    }

    /**
     * @return  The destination address.
     */
    public String getDestinationFound() {
        return destinationFound;
    }

    /**
     * @return The distance in KM.
     */
    public float getDistanceInKm() {
        return distanceInKm;
    }

    /**
     * @return The distance in HOURS.
     */
    public float getTimeInHours() {
        return timeInHours;
    }

    /**
     * @return The details of the shipping option.
     */
    public String getDetails() {
        return details;
    }

    /**
     * @return The calculated cost of the shipping option.
     */
    public float getCost() {
        return cost;
    }

    /**
     * @return The currency of the shipping option.
     */
    public Currency getCurrency() {
        return currency;
    }
}
