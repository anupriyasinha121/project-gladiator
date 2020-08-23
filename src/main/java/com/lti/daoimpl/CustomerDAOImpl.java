package com.lti.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.CustomerDAO;
import com.lti.entity.Claim;
import com.lti.entity.Customer;
import com.lti.entity.PolicyDetail;

@Repository
public class CustomerDAOImpl implements CustomerDAO {	
	
	@PersistenceContext(unitName = "Spring-JPA")
	EntityManager entityManager;
	
	
	@Transactional
	public boolean addCustomer(Customer customer) {
		
		try {
			entityManager.persist(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Transactional
	public boolean updateCustomer(Customer customer) {
		try {
			entityManager.merge(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional
	public String getPassword(String customerId) {	
		String passwd;
		try {
			Query query=entityManager.createQuery("select password from Customer where userId=:id");
			query.setParameter("id", customerId);
			passwd = (String)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return passwd;
	}

	
	@Transactional
	public boolean updateDrivingLicense(String customerId, String drivingLicense) {
		try {
			Query query = entityManager.createQuery("UPDATE Customer c SET c.drivingLicense =:arg1 where c.userId=:arg2");
			query.setParameter("arg1", drivingLicense);
			query.setParameter("arg2", customerId);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}				
		return true;
	}
	

	@Transactional
	public boolean updatePassword(String customerId, String password) {
	try {
		Query query = entityManager.createQuery("UPDATE Customer c SET c.password =:arg1 where c.userId=:arg2");
		query.setParameter("arg1", password);
		query.setParameter("arg2", customerId);
		query.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	return true;
	}
	

	@Transactional
	public Customer getCustomer(String customerId) {
		
		Customer custProfile;
		try {
			custProfile = entityManager.find(Customer.class, customerId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
		return custProfile;
		
	}

}
