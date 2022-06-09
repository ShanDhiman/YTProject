package com.example.YTlogin.model.request;

import com.example.YTlogin.Validations.EmailValidation;
import com.example.YTlogin.validation.group.OtpValidationGroup;

public class OTPRequest {
	
	@EmailValidation(groups = OtpValidationGroup.class)
	private String email;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
