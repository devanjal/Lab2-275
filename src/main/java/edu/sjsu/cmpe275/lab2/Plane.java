package edu.sjsu.cmpe275.lab2;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Plane {
    private int capacity;
	private String model; 
    private String manufacturer;
    @Column(name = "yearofmanufacture" )
    private int yearofmanufacture;
    
    public Plane(int capacity, String model, String manufacturer, int yearOfManufacture) {
		super();
		this.capacity = capacity;
		this.model = model;
		this.manufacturer = manufacturer;
		this.yearofmanufacture = yearOfManufacture;
	}
    public Plane(){}
    
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public int getYearOfManufacture() {
		return yearofmanufacture;
	}
	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearofmanufacture = yearOfManufacture;
	}
}
