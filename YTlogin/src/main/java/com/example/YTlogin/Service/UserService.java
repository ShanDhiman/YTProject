package com.example.YTlogin.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.example.YTlogin.model.request.DetailsRequest;
import com.example.YTlogin.model.request.OTPRequest;
import com.example.YTlogin.model.request.SignupRequest;
import com.example.YTlogin.model.response.ProfileResponse;
import com.example.YTlogin.model.response.ResponseModel;
import com.example.YTlogin.model.response.UIbean;

public interface UserService {
	
	void sendMail(OTPRequest  otpRequest);
	
	ResponseModel<?> verify(SignupRequest signupRequest, MultiValueMap<String, String> headers);
	
	ResponseEntity<?> updateUser(DetailsRequest detailsRequest , String email);
	
	UIbean<ProfileResponse> getUser(String email);
	

}
