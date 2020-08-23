package com.lti.daoimpl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lti.dao.DepriciationRateDAO;
import com.lti.entity.DepriciationRate;

@Repository
public class DepriciationRateDAOImpl implements DepriciationRateDAO {
	
	@PersistenceContext(unitName = "Spring-JPA")
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
