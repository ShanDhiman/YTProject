package com.example.YTlogin.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.YTlogin.Validations.DobValidation;

public class DobValidator implements ConstraintValidator<DobValidation,String>
{

	@Override
	public boolean isValid(String dob, ConstraintValidatorContext context) {
		return Pattern.compile("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")
				.matcher(dob).matches();
	}
	

}
