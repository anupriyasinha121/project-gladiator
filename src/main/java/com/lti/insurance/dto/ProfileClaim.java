package com.lti.insurance.dto;

import java.util.Date;

public class ProfileClaim {

	private long policyId;
	private long claimId;
	private Date claimDate;
	private String claimStatus;
	private double claimAmount;
	private String claimReason;
	
	public long getClaimId() {
		return claimId;
	}
	public long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}
	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}
	public Date getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getClaimReason() {
		return claimReason;
	}
	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}
	
	@Override
	public String toString() {
		return "ProfileClaim [policyId=" + policyId + ", claimId=" + claimId + ", claimDate=" + claimDate
				+ ", claimStatus=" + claimStatus + ", claimAmount=" + claimAmount + ", claimReason=" + claimReason
				+ "]";
	}

}
