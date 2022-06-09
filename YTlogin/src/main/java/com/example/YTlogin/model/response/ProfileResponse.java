package com.example.YTlogin.model.response;

import java.time.LocalDate;
import java.util.List;

public class ProfileResponse {

	private String email;
	private String phnNumber;
	private String DOB;
	private String address;
	private Long totalPoints;
	private String refCode;
	private List<String> referralDetails;
	
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhnNumber() {
		return phnNumber;
	}
	public void setPhnNumber(String phnNumber) {
		this.phnNumber = phnNumber;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Long getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}
	public List<String> getReferralDetails() {
		return referralDetails;
	}
	public void setReferralDetails(List<String> referralDetails) {
		this.referralDetails = referralDetails;
	}
	
	
	
}
