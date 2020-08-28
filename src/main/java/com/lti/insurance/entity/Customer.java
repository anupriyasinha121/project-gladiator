package com.lti.insurance.entity;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Customer {
	
	@Id
	private String userId;
	
	@Column(length=20)
	private String userName;
	
	@Column(length=20)
	private String password;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(length=20)
	private String address;
	
	private long contactNumber;

	@Column(length=20)
	private String drivingLicense;

	
//	have relation with "Policy"
	@OneToMany(mappedBy="policyCustomerId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)	//mappedBy="policyCustomerId",
	private Set<PolicyDetail> policies; 
	
	
//	have relation with "Claim"
	@OneToMany(mappedBy="claimCustomerIdFk", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Claim> claimId;


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public long getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getDrivingLicense() {
		return drivingLicense;
	}


	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}


	public Set<PolicyDetail> getPolicies() {
		return policies;
	}


	public void setPolicies(Set<PolicyDetail> policies) {
		this.policies = policies;
	}


	public Set<Claim> getClaimId() {
		return claimId;
	}


	public void setClaimId(Set<Claim> claimId) {
		this.claimId = claimId;
	}


	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", userName=" + userName + ", password=" + password + ", dob=" + dob
				+ ", address=" + address + ", contactNumber=" + contactNumber + ", drivingLicense=" + drivingLicense
				+ ", policies=" + policies + ", claimId=" + claimId + "]";
	}

}
