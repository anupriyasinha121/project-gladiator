package com.lti.insurance.service.vehicle;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.dto.VehicleDetail;
import com.lti.insurance.entity.Vehicle;

@Service
public class VehicleServiceProvider {

	@Autowired
	VehicleDAO vehicleDAO;
	
	
	@Transactional
	public List<String> allManufacturerByType(int type) {	
		
		List<Vehicle> vehicleList = vehicleDAO.getAllManufacturerByType(type);
		
		System.out.println("Total vehicle " + vehicleList.size());
		
		List<String> manuList = new ArrayList<String>();
		
		for(Vehicle vehicle: vehicleList) {
			String manu = vehicle.getManufacturer();
			System.out.println(manu);
			if(!manuList.contains(manu)) {
				System.out.println("added "+ manu);
				manuList.add(manu);
			}
		}
		
		return manuList;
		
	}
	
	@Transactional
	public List<String> getAllModelByManu(VehicleDetail vehicleDetail) {
		int type = vehicleDetail.getType();
		String manu = vehicleDetail.getManufacturer();
		
		List<Vehicle> vehicleList = vehicleDAO.getAllModelByManu(manu, type);
		
		List<String> modelList = new ArrayList<String>();
		
		for(Vehicle vehicle: vehicleList)	{
			String model = vehicle.getModel();
			System.out.println(model);
			if(!modelList.contains(model)) {
				System.out.println("added" +  model);
				modelList.add(model);
			}
		}
		return modelList;
	}
	
	@Transactional
	public List<String> allModelByType(int type){
		List<String> modelList = new ArrayList<String>();
		
		List<Vehicle> vehicleList = vehicleDAO.getAllVehicle();
		System.out.println("Total Vehicle "+ vehicleList.size());
		
		for(Vehicle vehicle: vehicleList)	{
			String model = vehicle.getModel();
			System.out.println(model+ " VehicleType " + vehicle.getVehicleType());
			if(!modelList.contains(model) && vehicle.getVehicleType()==type) {
				System.out.println("added" +  model);
				modelList.add(model);
			}
		}
		
		return modelList;
	}
	
}
