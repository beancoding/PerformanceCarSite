package com.dmcliver.performancecars.domain;

import javax.persistence.*;

@Entity
@Table(name = "Model")
public class Model {

	private String name;

	private Make make;
	
	public Model(String modelName, Make make) {
		name = modelName;
		this.make = make;
	}
	
	public Model() {}
	
	@Id
	@Column(name = "Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne
	@JoinColumn(name = "MakeId", nullable = false)
	public Make getMake() {
		return make;
	}
	public void setMake(Make make) {
		this.make = make;
	}
}
