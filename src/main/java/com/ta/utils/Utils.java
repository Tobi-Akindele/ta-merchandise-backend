/**
 * 
 */
package com.ta.utils;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.exceptions.BadRequestException;

/**
 * @author oyindamolaakindele
 *
 */

public class Utils {

	private Utils() {
	}

	public static boolean isEmptyString(String str) {
		return str == null || str.equalsIgnoreCase("") || str.equalsIgnoreCase("null");
	}

	public static boolean isEmpty(Object object) {
		return object == null || object.equals("");
	}

	@SuppressWarnings("unchecked")
	public static void validateRequiredFields(Object object, String[] requiredFields) {
		if (object != null && requiredFields != null && requiredFields.length != 0) {
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> objectFields = mapper.convertValue(object, HashMap.class);
			for (String field : requiredFields) {
				if (!objectFields.containsKey(field))
					throw new BadRequestException("400", field + " is required");
				if (objectFields.containsKey(field) && isEmpty(objectFields.get(field)))
					throw new BadRequestException("400", field + " cannot be empty or null");
			}
		}
	}
}
