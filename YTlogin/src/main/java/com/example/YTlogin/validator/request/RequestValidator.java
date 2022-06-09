package com.example.YTlogin.validator.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.tomcat.util.http.parser.Vary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.YTlogin.model.request.DetailsRequest;
import com.example.YTlogin.model.request.OTPRequest;
import com.example.YTlogin.model.request.SignupRequest;
import com.example.YTlogin.model.response.ResponseModel;
import com.example.YTlogin.validation.group.OtpValidationGroup;
import com.example.YTlogin.validation.group.VerifyValidationGroup;

@Component
public class RequestValidator {
	

	    @Autowired
	    private Validator validator;
	    
	    public ResponseModel<?> validateDetailRequest(DetailsRequest detailsRequest) {
	        Set<ConstraintViolation<DetailsRequest >> validate = validator.validate(detailsRequest,
	                VerifyValidationGroup.class);
	        return getResponseOnSignupValidation(validate);
	    }

	    public ResponseModel<?> validateOtpRequest(OTPRequest otpRequest) {
	        Set<ConstraintViolation<OTPRequest>> validate = validator.validate(otpRequest,
	                OtpValidationGroup.class);
	        return getResponseOnValidation(validate);
	    }

	    private ResponseModel<?> getResponseOnValidation(
	            Set<ConstraintViolation<OTPRequest>> validate) {

	        if (!validate.isEmpty()) { 
	            Map<String, String> errors = new HashMap<String, String>();
	            for (ConstraintViolation<OTPRequest> constraintViolation : validate) {
	                if (constraintViolation.getPropertyPath().toString() != null
	                        && !constraintViolation.getPropertyPath().toString().isEmpty())
	                    errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
	                else {
	                    String[] result = constraintViolation.getMessage().split(":");
	                    return new ResponseModel<>(HttpStatus.BAD_REQUEST, result[1], null, null);
	                }}
	            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Validation errors", null, null, errors);
	        }
	        return null;
	    }
	    
	    private ResponseModel<?> getResponseOnSignupValidation(
	            Set<ConstraintViolation<DetailsRequest>> validate) {

	        if (!validate.isEmpty()) { 
	            Map<String, String> errors = new HashMap<String, String>();
	            for (ConstraintViolation<DetailsRequest> constraintViolation : validate) {
	                if (constraintViolation.getPropertyPath().toString() != null
	                        && !constraintViolation.getPropertyPath().toString().isEmpty())
	                    errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
	                else {
	                    String[] result = constraintViolation.getMessage().split(":");
	                    return new ResponseModel<>(HttpStatus.BAD_REQUEST, result[1], null, null);
	                }}
	            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Validation errors", null, null, errors);
	        }
	        return null;
	     }
	   }


