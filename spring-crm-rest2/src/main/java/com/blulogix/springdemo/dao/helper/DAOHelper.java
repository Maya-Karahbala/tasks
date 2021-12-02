package com.blulogix.springdemo.dao.helper;

import java.math.BigDecimal;

public class DAOHelper {
	public static Object getObjectFromType(Class<?> type, Object object) {
		if(type.isInstance(object))
			return object;
		String typeName = type.getSimpleName();
		if(typeName.equals("String"))
			return object.toString();
		if(typeName.equals("Integer"))
			return Integer.parseInt(object.toString());
		if(typeName.equals("Long"))
			return Long.parseLong(object.toString());
		if(typeName.equals("Double"))
			return Double.parseDouble(object.toString());
		if(typeName.equals("Integer"))
			return Integer.parseInt(object.toString());
		if(typeName.equals("BigDecimal"))
			return new BigDecimal(object.toString());
		if(typeName.equals("Byte"))
			return Byte.parseByte(object.toString());
		if(typeName.equals("Short"))
			return Short.parseShort(object.toString());
		if(typeName.equals("Float"))
			return Float.parseFloat(object.toString());
		return object;
	}
}
