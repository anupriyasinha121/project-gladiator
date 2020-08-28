package com.lti.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.insurance.dto.VehicleDetail;
import com.lti.insurance.service.vehicle.VehicleServiceProvider;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

	@Autowired
	VehicleServiceProvider vehicleServiceProvider;
	
	@PostMapping("/manufacturers")
	public List <String> allManufacturerByType(@RequestBody String type) {
		
		System.out.println("type "+type);
		List <String> manuList = vehicleServiceProvider.allManufacturerByType(Integer.parseInt(type));
		System.out.println("Total Result "+ manuList.size());
		return manuList;
	}	
	
	@PostMapping("/models-by-type")
	public List<String> allModelsByType(@RequestBody String type) {
		
		System.out.println("type "+type);
		List <String> modelList = vehicleServiceProvider.allModelByType(Integer.parseInt(type));
		System.out.println("Total Result "+ modelList.size());
		return modelList;
	}
		
	@PostMapping("/models")
	public List<String> allModelByManu(@RequestBody VehicleDetail vehicleDetail) {
		
		List<String> modelList = vehicleServiceProvider.getAllModelByManu(vehicleDetail);	;
		System.out.println("Model Result " +  modelList.size());
		return modelList;
		
	}
}
