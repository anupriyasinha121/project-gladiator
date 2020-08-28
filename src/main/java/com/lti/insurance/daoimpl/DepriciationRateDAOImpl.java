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
import com.lti.insurance.dao.DepriciationRateDAO;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.DepriciationRate;

@Repository
public class DepriciationRateDAOImpl implements DepriciationRateDAO {
	
	@PersistenceContext//(unitName = "Spring-JPA")
	EntityManager entityManager;
	
	@Transactional
	public void insertdepr(DepriciationRate depr) {
		entityManager.persist(depr);
	}
	
	
	@Transactional
	public int selectdepRateByAge(double arg1)
	{
		Query query = entityManager.createQuery("SELECT dr.depriciationPercentage from DepriciationRate dr where :arg1>dr.minAge AND :arg1<=dr.maxAge");
		query.setParameter("arg1", arg1);
		int result=(Integer) query.getSingleResult();
		return result;
	}

}
