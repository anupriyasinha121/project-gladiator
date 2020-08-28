package com.lti.insurance.daoimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.insurance.dao.VehicleDAO;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.Vehicle;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

	@PersistenceContext//(unitName = "Spring-JPA")
	EntityManager entityManager;
	
	
	@Transactional
	public List<Vehicle> getAllVehicle(){
		List<Vehicle> vehicleList = null;
		
		Query query = entityManager.createQuery("Select v from Vehicle  v");
		vehicleList =  query.getResultList();
		return vehicleList;
	}
	
	@Transactional
	public Vehicle getVehicle(long vehicleID) {
		Vehicle vehicle = null;
		try {
			vehicle  = entityManager.find(Vehicle.class,  vehicleID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return vehicle;
	}
	
	@Transactional
	public boolean addVehicle(Vehicle vehicle) {
		
		try {
			entityManager.persist(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Transactional
	public List <Vehicle> getAllManufacturerByType(int type) {
		List<Vehicle> vehicle = null;
		
		try {
			Query query = entityManager.createQuery("Select v from Vehicle  v where v.vehicleType =:typevalue");
			query.setParameter("typevalue", type);
			vehicle=  query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return vehicle;
		}
		return vehicle;
	}
	
	@Transactional	
	public List<Vehicle> getAllModelByManu(String manufacturer, int type) {
		
		List<Vehicle> vehicle = null;
		
		try {
			Query query = entityManager.createQuery("Select v from Vehicle  v where v.manufacturer =:manufacturervalue and v.vehicleType=:typevalue");
			query.setParameter("manufacturervalue", manufacturer);
			query.setParameter("typevalue",type);
			
			vehicle =  query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return vehicle;
		}
		
		return vehicle;
	}

	@Transactional
	public Vehicle getvehicleByModel(String model, int type) {
		
		Vehicle vehicle = null;
		
		try {
			Query query = entityManager.createQuery("Select v from Vehicle v where v.model =:arg3 and v.vehicleType=:arg4");
			query.setParameter("arg3", model);
			query.setParameter("arg4", type);
			vehicle= (Vehicle) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return vehicle;
		} 
		return  vehicle;
	}
	@Transactional
	public boolean updateVehicle(Vehicle vehicle) {
		entityManager.merge(vehicle);
		return false;
	}

}
