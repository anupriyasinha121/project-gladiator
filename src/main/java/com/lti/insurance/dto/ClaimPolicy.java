package com.lti.insurance.dto;

public class ClaimPolicy {

	private long policyNumber;
	private long mobileNumber;
	private String claimReason;
	
	public long getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getClaimReason() {
		return claimReason;
	}
	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}
	@Override
	public String toString() {
		return "ClaimPolicy [policyNumber=" + policyNumber + ", mobileNumber=" + mobileNumber + ", claimReason="
				+ claimReason + "]";
	}
	
}
