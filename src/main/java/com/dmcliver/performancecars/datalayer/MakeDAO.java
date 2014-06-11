package com.dmcliver.performancecars.datalayer;

import java.util.List;

import com.dmcliver.performancecars.domain.Make;

public interface MakeDAO {

	List<?> findAll();
	void save(Make make);
	Make findByName(String selectedMake);
}