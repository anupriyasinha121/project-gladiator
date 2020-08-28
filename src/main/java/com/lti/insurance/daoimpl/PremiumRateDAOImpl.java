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
import com.lti.insurance.dao.PremiumRateDAO;
import com.lti.insurance.entity.Customer;
import com.lti.insurance.entity.DepriciationRate;
import com.lti.insurance.entity.PremiumRate;

@Repository
public class PremiumRateDAOImpl implements PremiumRateDAO {
	
	@PersistenceContext//(unitName = "Spring-JPA")
	EntityManager entityManager;
	
	@Transactional
	public void insertPremRate(PremiumRate perm) {
		entityManager.persist(perm);
	}
	
	
	@Transactional
	public PremiumRate selectPremRateByAge(int type,int enginePower)
	{
		Query query = entityManager.createQuery("from PremiumRate pr where pr.vehicleType=:arg1 AND :arg2>pr.minEnginePower AND :arg2<=pr.maxEnginePower");
		query.setParameter("arg1", type);
		query.setParameter("arg2", enginePower);
		List<PremiumRate>perm=query.getResultList();
		return perm.get(0);
		
	}

}

