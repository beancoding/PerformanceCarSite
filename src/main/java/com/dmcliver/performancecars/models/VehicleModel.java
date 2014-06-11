package com.dmcliver.performancecars.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class VehicleModel {
	
	private String modelName;
	private BigDecimal engineSize;

	private String engineType;
	private String engineAspiration;
	private BigDecimal quarterMileTime;
	private BigDecimal timeToOneHundred;
	private int century = 20;
	private int decade = 1;
	private int year = 1;
	private boolean existingModel = true;
	private String selectedMake;
	
	@NotBlank(message = "*The model name cannot be blank")
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String name) {
		this.modelName = name;
	}
	
	@NotNull(message = "*Please enter a size for the engine")
	@Range(message = "*Please enter a valid engine size", min = 1)
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
	
	public String getSelectedMake() {
		return selectedMake;
	}
	public void setSelectedMake(String selectedMake) {
		this.selectedMake = selectedMake;
	}
	
	public boolean isExistingModel() {
		return existingModel;
	}
	public void setExistingModel(boolean existingModel) {
		this.existingModel = existingModel;
	}
}