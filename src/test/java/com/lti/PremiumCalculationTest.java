package com.lti;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.insurance.dao.DepriciationRateDAO;
import com.lti.insurance.dao.PremiumRateDAO;
import com.lti.insurance.entity.DepriciationRate;
import com.lti.insurance.entity.PremiumRate;


@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class PremiumCalculationTest {

	@Autowired
	DepriciationRateDAO depr;
	
	@Autowired
	PremiumRateDAO prem;

	@Test
	public void testInsertDep() {
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
		PremiumRate p7=new PremiumRate(4,1500,20000,2.1,7890);
		
		
		prem.insertPremRate(p2);
		prem.insertPremRate(p3);
		prem.insertPremRate(p4);
		prem.insertPremRate(p5);
		prem.insertPremRate(p6);
		prem.insertPremRate(p7);
	}
	
}
