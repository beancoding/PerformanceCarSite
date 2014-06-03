package com.dmcliver.performancecars.domain;

import com.dmcliver.performancecars.Tag;

public enum EngineType {
	
	DOHC,
	@Tag("Straight Six")
	straightSix,
	@Tag("V6")
	v6,
	@Tag("V8")
	v8
}

