MayanValidator
==============

MayanValidator is a JSON declaration based backend validation framework. In a typical MVC architecture, it eases validation of back-end models (POJO classes) by declaring the validation requirements in a Json file.
The Json validation file can be easily updated by a member of the project's functional centric team cutting down knowledge transition time, etc.

Compiling:
From the root of the project (where the .pom file is present) execute 

    $ mvn install

Usage:
Provide the required validations to be used on the Models in a Json file (the validation.json file at src/test/java can be used as a reference point).
Instantiate the GenericValidator class which takes the validation.json file's path as its only argument.
After setting the state of the POJOs pass them as an argument to the validate() method of the GenericValidator:

    TestModel target = new TestModel();
    target.setId(10);
    target.setShortName("Short name");
    Error error = genericValidator.validate(target);
    
    
The validate method returns an object of the Error class with values set for failed validations per field.
