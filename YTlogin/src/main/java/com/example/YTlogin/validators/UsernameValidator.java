package com.example.YTlogin.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.YTlogin.Validations.UsernameValidation;

public class UsernameValidator implements ConstraintValidator<UsernameValidation, String> {

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return Pattern.compile("^\\pL+[\\pL\\pZ\\pP]{0,}")
        .matcher(name)
        .matches();
	}

}
