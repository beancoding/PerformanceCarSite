package com.dmcliver.performancecars.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ModelYear")
public class ModelYear {

	private Model model;
	private ModelYearPK modelYearPK;
	
	public ModelYear(Model model, ModelYearPK modelYearPK) {
		
		this.model = model;
		this.modelYearPK = modelYearPK;
	}
	
	public ModelYear() {}
	
	@ManyToOne
	@JoinColumn(name = "ModelId", insertable = false, updatable = false)
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	
	@EmbeddedId
	public ModelYearPK getModelYearPK() {
		return modelYearPK;
	}
	public void setModelYearPK(ModelYearPK modelYearPK) {
		this.modelYearPK = modelYearPK;
	}
}
