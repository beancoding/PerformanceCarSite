package com.dmcliver.performancecars.models;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

public class VehicleModel {
	
	private String modelName;
	private BigDecimal engineSize;
	private String engineType;
	private String engineAspiration;
	private BigDecimal quarterMileTime;
	private BigDecimal timeToOneHundred;
	private String make;
	private int century = 20;
	private int decade = 1;
	private int year = 1;
	
	@NotBlank(message = "*The model name cannot be blank")
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String name) {
		this.modelName = name;
	}
	
	public BigDecimal getEngineSize() {
		return engineSize;
	}
	public void setEngineSize(BigDecimal engineSize) {
		this.engineSize = engineSize;
	}
	
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	
	public String getEngineAspiration() {
		return engineAspiration;
	}
	public void setEngineAspiration(String engineAspiration) {
		this.engineAspiration = engineAspiration;
	}
	
	public BigDecimal getQuarterMileTime() {
		return quarterMileTime;
	}
	public void setQuarterMileTime(BigDecimal quarterMileTime) {
		this.quarterMileTime = quarterMileTime;
	}
	
	public BigDecimal getTimeToOneHundred() {
		return timeToOneHundred;
	}
	public void setTimeToOneHundred(BigDecimal timeToOneHundred) {
		this.timeToOneHundred = timeToOneHundred;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}

	public int getCentury() {
		return century;
	}
	public void setCentury(int century) {
		this.century = century;
	}
	
	public int getDecade() {
		return decade;
	}
	public void setDecade(int decade) {
		this.decade = decade;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}