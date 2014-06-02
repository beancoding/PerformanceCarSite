package com.dmcliver.performancecars;

public class StringExtras {

	public static final String emptyStr = "";
	
	public static boolean isNullOrEmpty(String data) {
		return data == null || data.equals(emptyStr);
	}
	
	public static boolean equalIgnoreCase(String s1, String s2) {
		
		if(s1 == null && s2 == null)
			return true;
		
		if(s1 != null && s2 != null && s1.toLowerCase().equals(s2.toLowerCase()))
			return true;
		
		return false;
	}
}
