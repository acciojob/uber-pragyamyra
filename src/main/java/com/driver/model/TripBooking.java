package com.driver.model;

import javax.persistence.*;
@Entity

public class TripBooking{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int tripBookingId;
    String fromLocation;
    String toLocation;
    int distanceInKm;
    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;
    @ManyToOne
    @JoinColumn
    Customer customer;
    @ManyToOne
     @JoinColumn
    Driver driver;



    public TripBooking(String fromLocation, String toLocation, int distanceInKm) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
    }

    public int getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


}