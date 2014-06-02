package com.dmcliver.performancecars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {

	private String name;
	private Continent continent;
	
	public Country(String country, Continent continent) {
		
		name = country;
		this.continent = continent;
	}
	
	public Country() {}
	
	@Id
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "ContinentId", nullable = false)
	public Continent getContinent() {
		return continent;
	}
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
}
