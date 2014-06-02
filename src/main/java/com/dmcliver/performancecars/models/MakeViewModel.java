package com.dmcliver.performancecars.models;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class MakeViewModel {

	private String continentId;
	private String countriesId;
	private String makeName;
	
	public MakeViewModel() {}

	public String getContinentId() {
		return continentId;
	}
	public void setContinentId(String continent) {
		this.continentId = continent;
	}

	public String getCountriesId() {
		return countriesId;
	}
	public void setCountriesId(String countriesId) {
		this.countriesId = countriesId;
	}

	@NotBlank(message = "*Please enter a name for the make")
	@Size(min = 3, message = "*The makes name must contain at least 3 letters")
	public String getMakeName() {
		return makeName;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
}
