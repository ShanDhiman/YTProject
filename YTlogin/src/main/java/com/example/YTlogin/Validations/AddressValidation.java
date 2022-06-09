package com.example.YTlogin.Validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.YTlogin.validators.AddressValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressValidator.class)
public @interface AddressValidation {
	
	String message() default "Invalid Address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	

}
