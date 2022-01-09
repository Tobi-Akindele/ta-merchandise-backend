package com.ta.utils;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;

public class BeanUtils<T, U> {

	private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	public T copyUpdatableFields(T target, U source, String[] updatableFields)
			throws NoSuchFieldException, SecurityException {
		if (target == null || source == null || updatableFields == null || updatableFields.length == 0)
			return null;

		try {
			final BeanWrapper src = new BeanWrapperImpl(source);
			final BeanWrapper trg = new BeanWrapperImpl(target);

			for (String fieldName : updatableFields) {
				Field field = target.getClass().getDeclaredField(fieldName);
				Object providedObject = src.getPropertyValue(field.getName());
				trg.setPropertyValue(field.getName(), providedObject);
			}

			return target;
		} catch (BeansException | NoSuchFieldException | SecurityException ex) {
			logger.error("[-] Error occurred {}", ex);
			throw ex;
		}
	}
}
