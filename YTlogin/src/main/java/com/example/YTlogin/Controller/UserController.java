
package com.example.YTlogin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.YTlogin.Redis.RedisUtility;
import com.example.YTlogin.Service.UserService;
import com.example.YTlogin.model.request.DetailsRequest;
import com.example.YTlogin.model.request.OTPRequest;
import com.example.YTlogin.model.request.SignupRequest;
import com.example.YTlogin.model.response.ResponseModel;
import com.example.YTlogin.validator.request.RequestValidator;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RedisUtility redisUtility;
	
	@Autowired
	private RequestValidator requestValidator;



	@PostMapping("/otp")
	public ResponseEntity<?> genOTP(@RequestBody OTPRequest otpRequest) {
		ResponseModel<?> responseModel = requestValidator.validateOtpRequest(otpRequest);
        if (responseModel != null)
            return ResponseEntity.badRequest().body(responseModel);
		
		
		userService.sendMail(otpRequest);
		
		return ResponseEntity.ok(new ResponseModel<>(200, "OTP sent successfully", null, null));

	}

	@PostMapping("/verify")
	public ResponseModel<?> verOTP(@RequestBody SignupRequest signupRequest,
			@RequestHeader MultiValueMap<String, String> headers) {
          
		return userService.verify(signupRequest, headers);
	}

	
	@PostMapping("/details")
	public ResponseEntity<?> details(@RequestHeader(required=false, value="user-mail")String email,@RequestBody DetailsRequest detailsRequest , 
			            @RequestHeader MultiValueMap<String, String> headers)
	{
		ResponseModel<?> responseModel = requestValidator.validateDetailRequest(detailsRequest);
        if (responseModel != null)
            return ResponseEntity.badRequest().body(new ResponseModel<>(HttpStatus.BAD_REQUEST, null, null, responseModel));
		
		return userService.updateUser(detailsRequest, email);
		
		
	}
	
	
	
}
