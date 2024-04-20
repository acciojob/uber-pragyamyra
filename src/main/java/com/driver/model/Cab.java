package com.driver.model;

import javax.persistence.*;

@Entity
public class Cab{
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

   int ratePerKm;
    boolean availability;

    public Cab(int id, int ratePerKm, boolean availability, Driver driver) {
        this.id = id;
        this.ratePerKm = ratePerKm;
        this.availability = availability;
        this.driver = driver;
    }

    @OneToOne
    @JoinColumn
    Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Cab() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(int ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}