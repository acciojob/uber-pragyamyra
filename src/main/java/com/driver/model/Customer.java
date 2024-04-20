package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
     @OneToMany
     List<TripBooking> bookings;

}