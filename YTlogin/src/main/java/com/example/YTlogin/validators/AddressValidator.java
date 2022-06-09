package com.example.YTlogin.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.YTlogin.Validations.AddressValidation;

public class AddressValidator implements ConstraintValidator<AddressValidation,String> {

	@Override
	public boolean isValid(String address, ConstraintValidatorContext context) {
		return Pattern.compile("^[#.0-9a-zA-Z\\s,-]+$")
				.matcher(address)
				.matches();
	}
}
