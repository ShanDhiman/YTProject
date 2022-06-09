package com.example.YTlogin.Validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.YTlogin.validators.EmailValidator;
import com.example.YTlogin.validators.PhoneNumValidator;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumValidator .class)
@Documented
public @interface PhoneNumValidation {
	
	String message() default "Invalid Phone Number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
