package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdCardValidator implements ConstraintValidator<IdCard, String>{

	public void initialize(IdCard paramA) {
	}

	public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
		if (phoneNo == null) {
			return false;
		}
		//return phoneNo.matches("\\d{10,12}");
		return phoneNo.matches("[0-9]+");
	}
	
}
