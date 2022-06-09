package com.example.YTlogin.model.request;

import java.time.LocalDate;

import com.example.YTlogin.Validations.AddressValidation;
import com.example.YTlogin.Validations.DobValidation;
import com.example.YTlogin.Validations.PhoneNumValidation;
import com.example.YTlogin.Validations.UsernameValidation;
import com.example.YTlogin.validation.group.VerifyValidationGroup;

public class DetailsRequest {
	
	@PhoneNumValidation(groups = VerifyValidationGroup.class)
	private String phnNumber;
	@DobValidation(groups = VerifyValidationGroup.class)
	private String dob;
	@UsernameValidation(groups = VerifyValidationGroup.class)
	private String name;
	@AddressValidation(groups = VerifyValidationGroup.class)
	private String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhnNumber() {
		return phnNumber;
	}
	public void setPhnNumber(String phnNumber) {
		this.phnNumber = phnNumber;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}

}
