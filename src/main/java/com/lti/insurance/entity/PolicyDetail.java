package com.lti.insurance.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PolicyDetail {

	@Id
	@GeneratedValue
	private long policyId;
	
	private double premiumAmount;	
	
	@Column(length=20)
	private String plan;
	
	@Temporal(TemporalType.DATE)
	private Date policyStartDate;
	
	@Temporal(TemporalType.DATE)
	private Date policyEndDate;
	
	@GeneratedValue
	private long transactionId;
	
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
//	relation with "RegisteredVehicleDetail"
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="reg_vehicle_id_fk")
	private RegisteredVehicleDetail regVehicleId ;
	
//	relation with "customer"
	@ManyToOne(fetch = FetchType.EAGER)	//(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id_fk")
	private Customer policyCustomerId;
	
	
//	relation with "vehicle"
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="vehicle_id_fk")
	private Vehicle policyVehicleId;

	
//	relation with "Claim"
	@OneToMany(mappedBy="claimPolicyId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Claim> policyClaimId;
	
	
	public long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Date getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(Date policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public Date getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(Date policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public RegisteredVehicleDetail getRegVehicleId() {
		return regVehicleId;
	}

	public void setRegVehicleId(RegisteredVehicleDetail regVehicleId) {
		this.regVehicleId = regVehicleId;
	}

	public Customer getPolicyCustomerId() {
		return policyCustomerId;
	}

	public void setPolicyCustomerId(Customer policyCustomerId) {
		this.policyCustomerId = policyCustomerId;
	}

	public Vehicle getPolicyVehicleId() {
		return policyVehicleId;
	}

	public void setPolicyVehicleId(Vehicle policyVehicleId) {
		this.policyVehicleId = policyVehicleId;
	}

	public Set<Claim> getPolicyClaimId() {
		return policyClaimId;
	}

	public void setPolicyClaimId(Set<Claim> policyClaimId) {
		this.policyClaimId = policyClaimId;
	}
	
}
