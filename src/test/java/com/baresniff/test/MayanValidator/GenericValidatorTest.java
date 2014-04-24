package com.baresniff.test.MayanValidator;

import com.baresniff.MayanValidator.GenericValidator;

import junit.framework.TestCase;

public class GenericValidatorTest extends TestCase {

	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenericValidator() {
		String validationJsonFilePath = "/home/prasoon/workspace/MayanValidator/src/test/java/validation.json"; 
		GenericValidator genericValidator = new GenericValidator(validationJsonFilePath);
		if(genericValidator.equals(null)){
			fail("Failed to instantiate the class");
		}		
	}

	public void testValidate() {
		String validationJsonFilePath = "/home/prasoon/workspace/MayanValidator/src/test/java/validation.json"; 
		GenericValidator genericValidator = new GenericValidator(validationJsonFilePath);
		if(genericValidator.equals(null)){
			fail("Failed to instantiate the class");
		}
		TestModel target = new TestModel();
		target.setId(10);
		target.setShortName("short name");
		genericValidator.validate(target);
	}

}
