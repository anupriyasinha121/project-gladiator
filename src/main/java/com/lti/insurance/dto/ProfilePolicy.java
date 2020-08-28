package com.lti.insurance.dto;

import java.util.Date;

public class ProfilePolicy {

	private long policyId;
	private String plan;
	private Date policyStartDate;
	private Date policyEndDate;
	private String model;
	private String registrationNumber;
	
	
	public long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(long policyId) {
		this.policyId = policyId;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	@Override
	public String toString() {
		return "ProfilePolicy [policyId=" + policyId + ", plan=" + plan + ", policyStartDate=" + policyStartDate
				+ ", policyEndDate=" + policyEndDate + ", model=" + model + ", registrationNumber=" + registrationNumber
				+ "]";
	}
	
	
	
}
