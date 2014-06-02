package com.dmcliver.performancecars.models;

public class CountryModel {

	private String name;

	public CountryModel(String name) {
		this.name = name;
	}
	
	public CountryModel() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
