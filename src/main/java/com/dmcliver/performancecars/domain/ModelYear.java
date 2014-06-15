package com.dmcliver.performancecars.domain;

import static com.dmcliver.performancecars.EnumMetadata.getTag;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ModelYear")
public class ModelYear {

	private Model model;
	private ModelYearPK modelYearPK;
	private BigDecimal engineSize;
	private EngineType engineType;
	private EngineAspiration engineAspiration;	
	private BigDecimal quarterMileTime;
	private BigDecimal timeToOneHundred;
	private String filePath;
	
	public ModelYear(Model model, BigDecimal engineSize, ModelYearPK modelYearPK) {
		
		this.model = model;
		this.engineSize = engineSize;
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
	
	@Column(name = "FilePath")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Transient
	public String getEngineAspirationTag(){
		return getTag(engineAspiration);
	}
	
	@Override
	@Transient
	public String toString() {
		return getClass().getSimpleName();
	}
}
