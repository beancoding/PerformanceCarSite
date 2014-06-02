package com.dmcliver.performancecars.domain;

import javax.persistence.*;

@Entity
@Table(name = "Continent")
public class Continent {

	private String name;
	private boolean isNorthernHemisphere;
	
	public Continent(String name, boolean isNorthernHemisphere) {
		this.name = name;
		this.isNorthernHemisphere = isNorthernHemisphere;
	}
	
	public Continent() {}
	
	@Id
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IsNorthernHemisphere", nullable = false)
	public boolean isNorthernHemisphere() {
		return isNorthernHemisphere;
	}
	public void setNorthernHemisphere(boolean isNorthernHemisphere) {
		this.isNorthernHemisphere = isNorthernHemisphere;
	}
}
