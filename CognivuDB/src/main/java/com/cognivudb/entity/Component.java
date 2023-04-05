package com.cognivudb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Component {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Component_ID")
	private long componentID;
	
	@Column(name = "Component_Type")
	private String componentType;

	@Column(name = "Manufacturer")
	private String manufacturer;
	
	@Column(name = "Model")
	private String model;
	
	@Column(name = "Serial_Number")
	private String serialNumber;
	
	@Column(name = "Capacity")
	private String capacity;
	
	@ManyToOne(targetEntity = Asset.class)
	@JoinColumn(name = "Asset_ID")
	@JsonIgnoreProperties("component")
	private Asset asset;

	public long getComponentID() {
		return componentID;
	}

	public void setComponentID(long componentID) {
		this.componentID = componentID;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
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

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Component(long componentID, String componentType, String manufacturer, String model, String serialNumber,
			String capacity, Asset asset) {
		super();
		this.componentID = componentID;
		this.componentType = componentType;
		this.manufacturer = manufacturer;
		this.model = model;
		this.serialNumber = serialNumber;
		this.capacity = capacity;
		this.asset = asset;
	}

	public Component() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Component [componentID=" + componentID + ", componentType=" + componentType + ", manufacturer="
				+ manufacturer + ", model=" + model + ", serialNumber=" + serialNumber + ", capacity=" + capacity
				+ ", asset=" + asset + "]";
	}
	
}
