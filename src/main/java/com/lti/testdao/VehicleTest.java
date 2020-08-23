package com.lti.testdao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.dao.ClaimDAO;
import com.lti.dao.CustomerDAO;
import com.lti.dao.PolicyDetailDAO;
import com.lti.dao.RegisteredVehicleDetailDAO;
import com.lti.dao.VehicleDAO;
import com.lti.daoimpl.VehicleDAOImpl;
import com.lti.entity.Vehicle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:myspring.xml")
public class VehicleTest {
	
	@Autowired
	CustomerDAO customerDAOImpl;
	
	@Autowired
	RegisteredVehicleDetailDAO regVeh;
	
	@Autowired
	PolicyDetailDAO policyDetailDAO;
	
	@Autowired
	ClaimDAO claimDAOImpl;
	
	@Autowired
	VehicleDAO vehicle;
	
	@PersistenceContext(unitName = "Spring-JPA")
	EntityManager entityManager;
	
	@Test
	public void testAddVeh1() {
		
		Vehicle v1 = new Vehicle();
		v1.setVehicleType(4);
		v1.setManufacturer("Tata");
		v1.setModel("Indigo");
		v1.setPrice(800000);
		
		Vehicle v2 = new Vehicle();
		v2.setVehicleType(4);
		v2.setManufacturer("Nissan");
		v2.setModel("Micra");
		v2.setPrice(100000);
		
		Vehicle v3 = new Vehicle();
		v3.setVehicleType(4);
		v3.setManufacturer("Tata");
		v3.setModel("Nano");
		v3.setPrice(100000);
		
		Vehicle v4 = new Vehicle();
		v4.setVehicleType(2);
		v4.setManufacturer("");
		v4.setModel("Micra");
		v4.setPrice(100000);
		
		Vehicle v5 = new Vehicle();
		v5.setVehicleType(2);
		v5.setManufacturer("Royal Enfield");
		v5.setModel("Classic 350");
		v5.setPrice(100000);
		
		Vehicle v6 = new Vehicle();
		v6.setVehicleType(2);
		v6.setManufacturer("Royal Enfield");
		v6.setModel("Classic 500");
		v6.setPrice(100000);	
		
		vehicle.addVehicle(v1);
		vehicle.addVehicle(v2);
		vehicle.addVehicle(v3);
		vehicle.addVehicle(v4);
		vehicle.addVehicle(v5);
		vehicle.addVehicle(v6);
		
	}
	
	@Test
	public void testGetByType() {
		
		List<Vehicle> veh = vehicle.getAllManufacturerByType(4);
//		List<Vehicle> veh = vehicle.getAllManufacturerByType(2);
		
		for(Vehicle v: veh) {
			System.out.println(v.getManufacturer());
		}
		
	}
	
	@Test
	public void testGetVehByManu() {
		
		List<Vehicle> veh = vehicle.getAllModelByManu("Tata", 4);
//		List<Vehicle> veh = vehicle.getAllModelByManu("Taa", 4);
		
		for(Vehicle v: veh) {
			System.out.println(v.getModel());
		}	
	}
	
	@Test
	public void testGetVehModel() {
		System.out.println("started");
		Vehicle veh = vehicle.getvehicleByModel("Nano", 4);
//		List<Vehicle> veh = vehicle.getAllModelByManu("Taa", 4);
		
		if(veh!=null) {
			System.out.println(veh.getPrice());
		}		
	}
	
	@Test
	public void testGetVehById() {
		System.out.println("started");
		Vehicle veh = vehicle.getVehicle(6);
		
		if(veh!=null) {
			System.out.println(veh.getPrice());
		}		
	}
}
