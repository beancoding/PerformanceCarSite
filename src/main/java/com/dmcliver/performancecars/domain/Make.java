package com.dmcliver.performancecars.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Make")
public class Make {

	private String name;
	private String foundersName;
	private Calendar dateFounded;
	private Country country;
	
	public Make(String makeName, Country country) {
		name = makeName;
		this.country = country;
	}
	
	public Make() {}
	
	@Column(name = "FoundersName", nullable = true)
	public String getFoundersName() {
		return foundersName;
	}
	public void setFoundersName(String foundersName) {
		this.foundersName = foundersName;
	}
	
	@Column(name = "DateFounded", nullable = true)
	public Calendar getDateFounded() {
		return dateFounded;
	}
	public void setDateFounded(Calendar dateFounded) {
		this.dateFounded = dateFounded;
	}
	
	@Id
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "CountryId", nullable = false)
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@Override
	@Transient
	public String toString() {
		return getClass().getSimpleName();
	}
}
