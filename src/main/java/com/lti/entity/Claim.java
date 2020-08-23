package com.lti.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Claim {

	@Id
	@GeneratedValue
	private long claimId;
	
	@Temporal(TemporalType.DATE)
	private Date claimDate;
	
	@Column(length=20)
	private String claimReason;
	
	@Column(length=20)
	private String claimStatus;
	
	@Column(length=20)
	private double claimAmount;
	
	
//	relation with "Customer"
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id_fk")
	private Customer claimCustomerIdFk;
	
	
//	relation with "PolicyDetail"
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="policy_id_fk")
	private PolicyDetail claimPolicyId;
	
	
	
	public long getClaimId() {
		return claimId;
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

	public String getClaimReason() {
		return claimReason;
	}

	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
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

	public Customer getClaimCustomerIdFk() {
		return claimCustomerIdFk;
	}

	public void setClaimCustomerIdFk(Customer claimCustomerIdFk) {
		this.claimCustomerIdFk = claimCustomerIdFk;
	}

	public PolicyDetail getClaimPolicyId() {
		return claimPolicyId;
	}

	public void setClaimPolicyId(PolicyDetail claimPolicyId) {
		this.claimPolicyId = claimPolicyId;
	}

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", claimDate=" + claimDate + ", claimReason=" + claimReason
				+ ", claimStatus=" + claimStatus + ", claimAmount=" + claimAmount + ", claimCustomerIdFk="
				+ claimCustomerIdFk + ", claimPolicyId=" + claimPolicyId + "]";
	}


}
