package com.dmcliver.performancecars.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ModelYearPK implements Serializable{

	private static final long serialVersionUID = 614900941300748686L;
	
	private String modelId;
	private int year;
	
	public ModelYearPK(String modelId, int year) {
		
		this.modelId = modelId;
		this.year = year;
	}
	
	public ModelYearPK() {}
	
	@Column(name = "Year")
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@Column(name = "ModelId")
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
}
