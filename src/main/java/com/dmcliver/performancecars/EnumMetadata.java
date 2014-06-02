package com.dmcliver.performancecars;

import java.lang.reflect.Field;

public class EnumMetadata {
	
	public static <T extends Enum<T>> String getTag(Enum<T> e) {
		
		Field[] fields = e.getClass().getFields();
		String name = e.name();
		Field field = null;
		field = findFieldWithName(fields, name);
		
		if(field == null) return e.toString();
		
		Tag tag = field.getAnnotation(Tag.class);
		return tag == null? e.toString() : tag.value();
	}

	private static Field findFieldWithName(Field[] fields, String name) {
		
		for (Field f: fields) {
			
			if(f.getName().equals(name)) {
				
				return f;
			}
		}
		return null;
	}
}