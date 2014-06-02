package com.dmcliver.performancecars.domain;

import static com.dmcliver.performancecars.EnumMetadata.getTag;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "Model")
public class Model {

	private String name;
	private BigDecimal engineSize;
	private EngineType engineType;
	private EngineAspiration engineAspiration;
	private BigDecimal quarterMileTime;
	private BigDecimal timeToOneHundred;
	private Make make;
	
	@Id
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "EngineSize", nullable = false)
	public BigDecimal getEngineSize() {
		return engineSize;
	}
	public void setEngineSize(BigDecimal engineSize) {
		this.engineSize = engineSize;
	}
	
	@Column(name = "EngineType", nullable = false)
	public EngineType getEngineType() {
		return engineType;
	}
	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}
	
	@Column(name = "EngineAspiration", nullable = true)
	public EngineAspiration getEngineAspiration() {
		return engineAspiration;
	}
	public void setEngineAspiration(EngineAspiration engineAspiration) {
		this.engineAspiration = engineAspiration;
	}
	
	@Column(name = "QuarterMile", nullable = true)
	public BigDecimal getQuarterMileTime() {
		return quarterMileTime;
	}
	public void setQuarterMileTime(BigDecimal quarterMileTime) {
		this.quarterMileTime = quarterMileTime;
	}
	
	@Column(name = "TimeToOneHundred", nullable = true)
	public BigDecimal getTimeToOneHundred() {
		return timeToOneHundred;
	}
	public void setTimeToOneHundred(BigDecimal timeToOneHundred) {
		this.timeToOneHundred = timeToOneHundred;
	}
	
	@ManyToOne
	@JoinColumn(name = "MakeId", nullable = false)
	public Make getMake() {
		return make;
	}
	public void setMake(Make make) {
		this.make = make;
	}
	
	@Transient
	public String engineAspirationTag(){
		return getTag(engineAspiration);
	}
}
