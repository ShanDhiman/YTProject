package com.example.YTlogin.Entity;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;


@RedisHash("userotp")
public class OTPentity implements  Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	} 

}
