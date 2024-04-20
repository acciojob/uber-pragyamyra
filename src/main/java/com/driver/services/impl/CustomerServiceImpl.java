package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.model.Cab;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> OptionalCustomer= customerRepository2.findById(customerId);
		if(OptionalCustomer.isPresent()){
			Customer customer=OptionalCustomer.get();
			customerRepository2.delete(customer);
		}
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		TripBooking tripBooking = new TripBooking(fromLocation, toLocation, distanceInKm);
		Optional<Customer> OptionalCustomer= customerRepository2.findById(customerId);
		if(OptionalCustomer.isPresent()){
			Customer customer=OptionalCustomer.get();
			tripBooking.setCustomer(customer);
		}
		List<Driver> driverList=driverRepository2.findAll();

		for(Driver d:driverList){
			boolean cabAvailability= d.getCab().isAvailability();
			if(cabAvailability){
				tripBooking.setDriver(d);
				tripBooking.setTripStatus(TripStatus.CONFIRMED);
				tripBookingRepository2.save(tripBooking);
				break;
			}
		}
		//Avoid using SQL query
		Driver driver= tripBooking.getDriver();
		if(driver==null){
			throw new Exception("No cab available!");
		}

		return tripBooking;

	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tripBookingOptional = tripBookingRepository2.findById(tripId);
		if(tripBookingOptional.isPresent()){
			TripBooking tripBooking = tripBookingOptional.get();
			tripBooking.setTripStatus(TripStatus.CANCELED);
			tripBookingRepository2.save(tripBooking);
		}


	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tripBookingOptional = tripBookingRepository2.findById(tripId);
		if(tripBookingOptional.isPresent()){
			TripBooking tripBooking = tripBookingOptional.get();
			tripBooking.setTripStatus(TripStatus.COMPLETED);
			tripBookingRepository2.save(tripBooking);
		}

	}
}
