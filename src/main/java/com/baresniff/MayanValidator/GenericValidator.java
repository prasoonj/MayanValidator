package com.baresniff.MayanValidator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenericValidator {
	/*Provide fully qualified file name or absolute path*/
	private String validationJsonFile;
	private static ArrayList<String> allAvailableValidations = new ArrayList<String>();

	public GenericValidator(String validationJsonFile) {
		super();
		this.validationJsonFile = validationJsonFile;
	}

	public Errors validate(Object target) {
		/* Constructing Errors object */
		Errors errors = new Errors();
		
		String className = target.getClass().getName();
		FileReader jsonFile = null;
		try {
			jsonFile = new FileReader(validationJsonFile);
			FileInputStream fis = new FileInputStream(validationJsonFile);
			
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
			e.printStackTrace();
		}
		JSONObject fileJsonObject = JSONObject.fromObject(jsonFile);
		JSONObject validationsJsonObject = (JSONObject) fileJsonObject
				.get("validations");
		JSONObject modelJsonObject = (JSONObject) validationsJsonObject
				.get(className);
		Set<String> allFields = modelJsonObject.keySet();
		for (String field : allFields) {
			Object fieldValue = null;
			int fieldLength = 0;
			try {
				Field targetField = target.getClass().getDeclaredField(field);
				targetField.setAccessible(true);
				fieldValue = targetField.get(target);
				fieldLength = fieldValue.toString().length();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Set<String> allValidationTests = new HashSet<String>();
			allValidationTests.addAll(allAvailableValidations);
			JSONArray validations = (JSONArray) modelJsonObject.get(field);
			for (int i = 0; i < validations.size(); i++) {
				JSONObject validation = (JSONObject) validations.get(i);
				String test = (String) validation.names().get(0);
				String parameter = validation.getString(test);
				if (allValidationTests.contains(test)) {
					// take action according to the test
					if (test.equals("rejectIfEmptyOrWhitespace")) {
//						ValidationUtils.rejectIfEmptyOrWhitespace(errors,
//								field, parameter);
					} else if (test.equals("minLength")) {
						if (fieldLength < Integer.parseInt(parameter)) {
//							errors.rejectValue(field, "Minimum Length of "
//									+ field + " must be at least " + parameter
//									+ " characters long");
						}
					} else if (test.equals("maxLength")) {
						if (fieldLength > Integer.parseInt(parameter)) {
//							errors.rejectValue(field, "Minimum Length of "
//									+ field + " must not be more than "
//									+ parameter + " characters long");
						}
					}
					allValidationTests.remove(test);
				}
			}
		}
		return errors;

	}
}
