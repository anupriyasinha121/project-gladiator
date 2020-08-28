package com.lti.insurance.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class PremiumRate {

	private int vehicleType;
	
	private int minEnginePower;
		
	private int maxEnginePower;
	
	private double ownDamagePercentage;
	
	@Id
	private int tplPremium;

	public int getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getMinEnginePower() {
		return minEnginePower;
	}

	public void setMinEnginePower(int minEnginePower) {
		this.minEnginePower = minEnginePower;
	}

	public double getMaxEnginePower() {
		return maxEnginePower;
	}

	public void setMaxEnginePower(int maxEnginePower) {
		this.maxEnginePower = maxEnginePower;
	}

	public double getOwnDamagePercentage() {
		return ownDamagePercentage;
	}

	public void setOwnDamagePercentage(double ownDamagePercentage) {
		this.ownDamagePercentage = ownDamagePercentage;
	}

	public int getTplPremium() {
		return tplPremium;
	}

	public void setTplPremium(int tplPremium) {
		this.tplPremium = tplPremium;
	}

	public PremiumRate(int vehicleType, int minEnginePower, int maxEnginePower, double ownDamagePercentage,
			int tplPremium) {
		super();
		this.vehicleType = vehicleType;
		this.minEnginePower = minEnginePower;
		this.maxEnginePower = maxEnginePower;
		this.ownDamagePercentage = ownDamagePercentage;
		this.tplPremium = tplPremium;
	}

	public PremiumRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PremiumRate [vehicleType=" + vehicleType + ", minEnginePower=" + minEnginePower + ", maxEnginePower="
				+ maxEnginePower + ", ownDamagePercentage=" + ownDamagePercentage + ", tplPremium=" + tplPremium + "]";
	}
	
	
}


// select tplpremium from premiumrate where type-2/4 and p bwtween(minengine,maxengine)
// select tplpremium,owndamagepercentage from ....