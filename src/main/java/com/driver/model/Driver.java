package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Driver{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String mobile;
    String password;

    public Driver(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    @OneToOne(mappedBy = "driver",cascade = CascadeType.ALL)
    Cab cab;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    List<TripBooking> Bookings;



    public Driver(int id, String mobile, String password, Cab cab, List<TripBooking> bookings) {
        this.id = id;
        this.mobile = mobile;
        this.password = password;
        this.cab = cab;
        Bookings = bookings;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public List<TripBooking> getBookings() {
        return Bookings;
    }

    public void setBookings(List<TripBooking> bookings) {
        Bookings = bookings;
    }

    public Driver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}