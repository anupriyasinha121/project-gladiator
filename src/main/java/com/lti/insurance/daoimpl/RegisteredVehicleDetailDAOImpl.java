package com.lti.insurance.daoimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.insurance.dao.RegisteredVehicleDetailDAO;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.RegisteredVehicleDetail;


@Repository
public class RegisteredVehicleDetailDAOImpl implements RegisteredVehicleDetailDAO{

	
	@PersistenceContext//(unitName = "Spring-JPA")
	EntityManager entityManager;
	
	@Transactional
	public boolean addRegisteredVehicle(RegisteredVehicleDetail regDetail) {

		try {
			entityManager.persist(regDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	@Transactional
	public RegisteredVehicleDetail getRegisteredVehicle(int regId) {

		RegisteredVehicleDetail regVehicle = null;
		
		try {
			regVehicle = entityManager.find(RegisteredVehicleDetail.class, regId);
		} catch (Exception e) {
			e.printStackTrace();
			return regVehicle;
		}
		
		return regVehicle;
	}
	
	@Transactional
	public boolean updateRegisteredVehicle(RegisteredVehicleDetail regDetail) {
		
		try {
			entityManager.merge(regDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

}
