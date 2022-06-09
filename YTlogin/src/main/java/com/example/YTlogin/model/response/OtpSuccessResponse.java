package com.example.YTlogin.model.response;

public class OtpSuccessResponse {
	
	private String token; 
	private Boolean isExistingUser;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsExistingUser() {
		return isExistingUser;
	}
	public void setIsExistingUser(Boolean isExistingUser) {
		this.isExistingUser = isExistingUser;
	}

}
