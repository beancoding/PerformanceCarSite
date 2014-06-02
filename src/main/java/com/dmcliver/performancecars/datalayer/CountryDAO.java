package com.dmcliver.performancecars.datalayer;

import java.util.List;

import com.dmcliver.performancecars.domain.Country;

public interface CountryDAO {

	Country findById(String id);
	List<Country> findByContinentId(String id);
}