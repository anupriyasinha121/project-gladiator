package com.lti.insurance.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.Vehicle;

public interface VehicleDAO {
	
	public List<Vehicle> getAllVehicle();
	
	public boolean addVehicle(Vehicle vehicle);
	
	public Vehicle getVehicle(long vehicleID);
	
	public boolean updateVehicle(Vehicle vehicle);

	// For form : when customer select vehicle type
	List <Vehicle> getAllManufacturerByType(int type);					
	
	// For form : when customer select manufacturer
	List <Vehicle> getAllModelByManu(String manufacturer, int type);				
	
	// when customer select manufacturer and model
	Vehicle getvehicleByModel(String manufacturer, int type);		
}
