package com.dmcliver.performancecars.datalayer;

import java.util.List;

import com.dmcliver.performancecars.domain.Model;

public interface ModelDAO {

	public List<Model> findAll(String makeName);
}