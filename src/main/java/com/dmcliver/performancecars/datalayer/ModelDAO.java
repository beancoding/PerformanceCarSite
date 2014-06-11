package com.dmcliver.performancecars.datalayer;

import java.util.List;

import com.dmcliver.performancecars.domain.Model;
import com.dmcliver.performancecars.domain.ModelYear;

public interface ModelDAO {

	List<Model> findAll(String makeName);
	void save(Model model);
	void save(ModelYear modelYear);
	Model findByName(String modelName);
}