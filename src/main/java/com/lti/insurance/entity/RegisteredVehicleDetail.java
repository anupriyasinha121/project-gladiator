package com.lti.insurance.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class RegisteredVehicleDetail {

	@Id
	private String registrationNumber;
	
	@Temporal(TemporalType.DATE)
	private Date purchaseDate;
	
	private double enginePower;
	
	@Column(length=20)
	private String engineNumber;
	
	@Column(length=20)
	private String chassisNumber;
	
//	relation with "policy"
	@OneToMany(mappedBy="regVehicleId", cascade = CascadeType.ALL)
	private Set<PolicyDetail> policyId;
	
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public Set<PolicyDetail> getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Set<PolicyDetail> policyId) {
		this.policyId = policyId;
	}

	public double getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(double enginePower) {
		this.enginePower = enginePower;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	@Override
	public String toString() {
		return "RegisteredVehicleDetail [registrationNumber=" + registrationNumber + ", purchaseDate=" + purchaseDate
				+ ", enginePower=" + enginePower + ", engineNumber=" + engineNumber + ", chassisNumber=" + chassisNumber
				+ ", policyId=" + policyId + "]";
	}
	
}
