package com.example.YTlogin.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.YTlogin.Validations.PhoneNumValidation;

public class PhoneNumValidator implements ConstraintValidator<PhoneNumValidation, String> {

	@Override
	public boolean isValid(String phnNumber, ConstraintValidatorContext context) {
		 return Pattern.compile("^\\+?[1-9][0-9]{7,14}$")
         .matcher(phnNumber)
         .matches();
	}

}
