/**
 * 
 */
package com.ta.utils;

import java.util.HashMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.exceptions.BadRequestException;
import com.ta.models.User;
import com.ta.security.services.UserDetailsImpl;

/**
 * @author oyindamolaakindele
 *
 */

public class Utils {

	private Utils () {
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
	
	public static User getUserEmailIdFromContext() {
    	SecurityContext context = SecurityContextHolder.getContext();
    	Authentication auth = context == null ? null : context.getAuthentication();
    	UserDetailsImpl userDetails = auth == null? null : (UserDetailsImpl) auth.getPrincipal();
    	return userDetails != null ? userDetails.getUser() : null;
    }
	
	public static boolean isLoggedInUserAdmin() {
		boolean isAdmin = false;
    	SecurityContext context = SecurityContextHolder.getContext();
    	Authentication auth = context == null ? null : context.getAuthentication();
    	UserDetailsImpl userDetails = auth == null? null : (UserDetailsImpl) auth.getPrincipal();
    	isAdmin = userDetails != null ? userDetails.getUser().isAdmin() : false;
    	return isAdmin;
    }
	
	public static int wordsCount(String str) {
		return str.trim().split("\\s*,\\s*").length;
	}
}
