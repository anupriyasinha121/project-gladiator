package com.lti.insurance.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue
	private long vehicleId;
	
	private int vehicleType;
	
	private String manufacturer;
	
	private String model;
	
	private double price;
	
//	relation with "policy"
	@OneToMany(mappedBy = "policyVehicleId", cascade = CascadeType.ALL)
	private Set<PolicyDetail> policyId;

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<PolicyDetail> getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Set<PolicyDetail> policyId) {
		this.policyId = policyId;
	}

//	@Override
//	public String toString() {
//		return "Vehicle [vehicleId=" + vehicleId + ", vehicleType=" + vehicleType + ", manufacturer=" + manufacturer
//				+ ", model=" + model + ", price=" + price + ", policyId=" + policyId + "]";
//	}
	
}


















// select manufacturer from vehicle where vehicleType = 2/4;
// select model from vehicle where vehicleType=.... and manufacturere=...
// select price from vehicle where vehicleType=.... and manufacturere=... and model...
// select enginePower from vehicle where vehicleType=.... and manufacturere=... and model...

//*** select price from vehicle where vehicleType=.... and manufacturere=... and model-.. and enginePower=...
// 