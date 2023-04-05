package com.cognivudb.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Asset_ID")
	private long assetID;
	
	@Column(name = "Asset_Name")
	private String assetName;

	@Column(name = "Manufacturer")
	private String manufacturer;
	
	@Column(name = "Model")
	private String model;
	
	@Column(name = "Serial_Number")
	private String serialNumber;
	
	@Column(name = "Location")
	private String location;
	
	@Column(name = "Status")
	private String status;
	
	@OneToMany(mappedBy = "asset")
	@JsonIgnoreProperties("asset")
	private List<Component> component;

	public long getAssetID() {
		return assetID;
	}

	public void setAssetID(long assetID) {
		this.assetID = assetID;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Component> getComponent() {
		return component;
	}

	public void setComponent(List<Component> component) {
		this.component = component;
	}

	public Asset(long assetID, String assetName, String manufacturer, String model, String serialNumber,
			String location, String status, List<Component> component) {
		super();
		this.assetID = assetID;
		this.assetName = assetName;
		this.manufacturer = manufacturer;
		this.model = model;
		this.serialNumber = serialNumber;
		this.location = location;
		this.status = status;
		this.component = component;
	}

	public Asset() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Asset [assetID=" + assetID + ", assetName=" + assetName + ", manufacturer=" + manufacturer + ", model="
				+ model + ", serialNumber=" + serialNumber + ", location=" + location + ", status=" + status
				+ ", component=" + component + "]";
	}
	
	
}
