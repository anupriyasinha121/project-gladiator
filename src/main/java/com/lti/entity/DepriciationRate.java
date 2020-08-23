package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class DepriciationRate {
	
	@Id
	private double minAge;
	
	private double maxAge;
	
	private int depriciationPercentage;

	public double getMinAge() {
		return minAge;
	}

	public void setMinAge(double minAge) {
		this.minAge = minAge;
	}

	public double getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(double maxAge) {
		this.maxAge = maxAge;
	}

	public int getDepriciationPercentage() {
		return depriciationPercentage;
	}

	public void setDepriciationPercentage(int depriciationPercentage) {
		this.depriciationPercentage = depriciationPercentage;
	}

	public DepriciationRate(double minAge, double maxAge, int depriciationPercentage) {
		super();
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.depriciationPercentage = depriciationPercentage;
	}

	public DepriciationRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DepriciationRate [minAge=" + minAge + ", maxAge=" + maxAge + ", depriciationPercentage="
				+ depriciationPercentage + "]";
	}

}








//select depriciationPercentage from depriciationRate where p between(minAge,maxAge)
