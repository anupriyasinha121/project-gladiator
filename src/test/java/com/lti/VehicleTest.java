package com.lti;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lti.insurance.dao.ClaimDAO;
import com.lti.insurance.dao.CustomerDAO;
import com.lti.insurance.dao.DepriciationRateDAO;
import com.lti.insurance.dao.PolicyDetailDAO;
import com.lti.insurance.dao.PremiumRateDAO;
import com.lti.insurance.dao.RegisteredVehicleDetailDAO;
import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.entity.Claim;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.DepriciationRate;
import com.lti.insurance.entity.PolicyDetail;
import com.lti.insurance.entity.PremiumRate;
import com.lti.insurance.entity.RegisteredVehicleDetail;
import com.lti.insurance.entity.Vehicle;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
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
	
	@Autowired
	DepriciationRateDAO depr;
	
	@Autowired
	PremiumRateDAO prem;

	
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
		v4.setManufacturer("Honda");
		v4.setModel("splendor");
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
	public void testGetAllVehicle() {
		List<Vehicle> veh = vehicle.getAllVehicle();
		for(Vehicle v: veh) {
			System.out.println(v.getModel());
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
	
	
	@Test
	public void testInsertData() {
		DepriciationRate d1=new DepriciationRate(0,0.5,5);
		DepriciationRate d2=new DepriciationRate(0.5,1,15);
		DepriciationRate d3=new DepriciationRate(1,2,20);
		DepriciationRate d4=new DepriciationRate(2,3,30);
		DepriciationRate d5=new DepriciationRate(3,4,40);
		DepriciationRate d6=new DepriciationRate(4,5,50);
		DepriciationRate d7=new DepriciationRate(5,1000,50);
		depr.insertdepr(d1);
		depr.insertdepr(d2);
		depr.insertdepr(d3);
		depr.insertdepr(d4);
		depr.insertdepr(d5);
		depr.insertdepr(d6);
		depr.insertdepr(d7);
	}
	
	@Test
	public void testInsertPrem() {
		PremiumRate p1=new PremiumRate(2,0,75,0.9,482);
		prem.insertPremRate(p1);
		PremiumRate p2=new PremiumRate(2,75,150,1,752);
		PremiumRate p3=new PremiumRate(2,150,350,1.1,1193);
		PremiumRate p4=new PremiumRate(2,350,10000,1.2,2323);
		PremiumRate p5=new PremiumRate(4,0,1000,1.9,2072);
		PremiumRate p6=new PremiumRate(4,1000,1500,2,3221);
		PremiumRate p7=new PremiumRate(4,1500,10000,2.1,7890);
		
		
		prem.insertPremRate(p2);
		prem.insertPremRate(p3);
		prem.insertPremRate(p4);
		prem.insertPremRate(p5);
		prem.insertPremRate(p6);
		prem.insertPremRate(p7);
	}
	
	
}
