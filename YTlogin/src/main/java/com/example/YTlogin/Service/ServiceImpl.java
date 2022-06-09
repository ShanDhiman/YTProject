package com.example.YTlogin.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.example.YTlogin.Entity.DeviceDetailsEntity;
import com.example.YTlogin.Entity.LoginDetailsEntity;
import com.example.YTlogin.Entity.ReferralEntity;
import com.example.YTlogin.Entity.UserEntity;
import com.example.YTlogin.Redis.RedisUtility;
import com.example.YTlogin.Repository.DeviceDetRepository;
import com.example.YTlogin.Repository.LoginDetRepository;
import com.example.YTlogin.Repository.ReferralRepository;
import com.example.YTlogin.Repository.UserRepository;
import com.example.YTlogin.model.request.DetailsRequest;
import com.example.YTlogin.model.request.OTPRequest;
import com.example.YTlogin.model.request.SignupRequest;
import com.example.YTlogin.model.response.OtpSuccessResponse;
import com.example.YTlogin.model.response.ProfileResponse;
import com.example.YTlogin.model.response.ResponseModel;
import com.example.YTlogin.model.response.UIbean;

import org.apache.commons.lang3.RandomStringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ServiceImpl implements UserService {

	@Autowired
	private JavaMailSender javaMailsender;
	@Autowired
	RedisUtility redisUtility;
	@Autowired
	UserRepository userRepository;
	@Autowired
	DeviceDetRepository deviceDetRepo;
	@Autowired
	ReferralRepository referralRepo;
	@Autowired
	LoginDetRepository loginDetRepo;

	@Value("${auth.token.secret.key}")
	private String authSecret;
	@Value("${user.login.type1}")
	private String log_type;
	@Value("${user.login.referral.points}")
	private Long ref_points;
	@Value("${master.otp}")
	private String masterOtp;

	UserEntity userEntity = new UserEntity();

	@Async
	public void sendMail(OTPRequest otpRequest) {

		String email = otpRequest.getEmail();
		Random rnd = new Random();
		int number = rnd.nextInt(9999);
		String otp = String.format("%04d", number);
		String mail_key = otpRequest.getEmail() + "_OTP";

		redisUtility.setData(mail_key, otp);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("shubhamdhiman844@gmail.com");
		message.setTo(email);
		message.setSubject("OTP");
		message.setText(otp);
		try {
			javaMailsender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public ResponseModel<OtpSuccessResponse> verify(SignupRequest signupRequest,
			MultiValueMap<String, String> headers) {
		String email = signupRequest.getEmail();
		String otp = signupRequest.getOtp();
		String key = email + "_OTP";
		String dbOTP = redisUtility.getData(key);
		
		if (dbOTP != null || otp!=null) 
		{
		
		if (otp.equals(dbOTP) || otp.equals(masterOtp)) {

			redisUtility.delData(key);
			UserEntity userEntity = userRepository.findByEmail(email);
			UserEntity referrerUser = null;

			if (userEntity != null) {
				userEntity.setIs_active(true);
			} else {

				String code = RandomStringUtils.randomAlphabetic(8).toUpperCase();

				userEntity = new UserEntity();
				userEntity.setEmail(signupRequest.getEmail());
				userEntity.setIs_active(true);
				userEntity.setIs_deleted(false);
				userEntity.setReferral_code(code);

				if (signupRequest.getReferral_code() != null) {

					referrerUser = userRepository.findByRefCode(signupRequest.getReferral_code());

					if (referrerUser != null) {
						List<ReferralEntity> referralDetails = referrerUser.getReferralEntity();
						if (referralDetails == null)
							referralDetails = new ArrayList<ReferralEntity>();

						ReferralEntity referralEntity = new ReferralEntity();
						referralEntity.setReferralEmail(signupRequest.getEmail());
						referralEntity.setReferrerEmail(referrerUser.getEmail());
						referralEntity.setPoints(ref_points);
						referralDetails.add(referralEntity);

						referrerUser.setReferralEntity(referralDetails);
					} else {
						return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Invalid Referral Code", null, null);
					}
				}
			}

			LoginDetailsEntity login = userEntity.getLoginDetailsEntity();

			if (login == null) {
				login = new LoginDetailsEntity();
			}

			login.setLogin_type(log_type);

			userEntity.setLoginDetailsEntity(login);
			login.setUserEntity(userEntity);

			String token = generateToken(headers, userEntity);

			List<DeviceDetailsEntity> deviceDetails = login.getDeviceDetails();
			DeviceDetailsEntity device = new DeviceDetailsEntity();
			if (deviceDetails == null) {
				deviceDetails = new ArrayList<DeviceDetailsEntity>();
			}

			device.setDevicename(signupRequest.getDevice_name());
			device.setDevicemodel(signupRequest.getDevice_model());
			device.setToken(token);
			device.setIsActive(true);
			device.setLoginDetailsEntity(login);
			deviceDetails.add(device);

			login.setDeviceDetails(deviceDetails);

			try {
				if (referrerUser != null) {
					userRepository.saveAll(Arrays.asList(userEntity, referrerUser));
				} else {
					userRepository.save(userEntity);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			OtpSuccessResponse otpSuccessResponse = new OtpSuccessResponse();
			otpSuccessResponse.setIsExistingUser(true);
			otpSuccessResponse.setToken(token);

			return new ResponseModel<OtpSuccessResponse>(HttpStatus.OK, " OTP verified", null, otpSuccessResponse);

		    } 
		else
		return new ResponseModel<OtpSuccessResponse>(HttpStatus.BAD_REQUEST, "Invalid OTP", null, null);
		
		}
		else {
			
		return new ResponseModel<OtpSuccessResponse>(HttpStatus.BAD_REQUEST, "Invalid OTP", null, null);
		
		}

	}

	
	@Override
	public ResponseEntity<?> updateUser(DetailsRequest detailsRequest, String email) {
		UserEntity isExistingUser = userRepository.findByEmail(email);
		if (isExistingUser != null) {

			isExistingUser.setAddress(detailsRequest.getAddress());
			isExistingUser.setDob(detailsRequest.getDob());
			isExistingUser.setPh_number(detailsRequest.getPhnNumber());
			isExistingUser.setName(detailsRequest.getName());
			try {
				userRepository.save(isExistingUser);
			} catch (Exception e) {
				e.printStackTrace();
			}

			
			return ResponseEntity.ok(new ResponseModel<>(HttpStatus.OK, "User saved", null, null)) ;
		} else
			return ResponseEntity.badRequest().body(new ResponseModel<>(HttpStatus.BAD_REQUEST, "Could not update user's details", null, null));

	}

	@Override
	public UIbean<ProfileResponse> getUser(String email) {
		UserEntity isExistingUser = userRepository.findByEmail(email);
		UIbean<ProfileResponse> ui = new UIbean<ProfileResponse>();
		if (isExistingUser != null) {

			ProfileResponse profile = new ProfileResponse();

			profile.setEmail(isExistingUser.getEmail());
			profile.setDOB(isExistingUser.getDob());
			profile.setAddress(isExistingUser.getAddress());
			profile.setPhnNumber(isExistingUser.getPh_number());
			profile.setRefCode(isExistingUser.getReferral_code());

			List<ReferralEntity> referralList = isExistingUser.getReferralEntity();

			List<String> list = new ArrayList<>();

			Long points = (long) 0;
			for (ReferralEntity refEntity : referralList) {
				String referralEmail = refEntity.getReferralEmail();
				String maskedEmail = referralEmail.replaceAll("^([^@]{3})[^@]+", "$1***");// (?<=.{3}).(?=[^@]+@)

				Long getPoints = refEntity.getPoints();

				String refDetails = "Email : "+maskedEmail + " , points : "+ getPoints.toString();
				list.add(refDetails);
				
				points = points+getPoints;
				

			}
            profile.setTotalPoints(points);
			profile.setReferralDetails(list);

			ui.setUserData(profile);

			return ui;
		}
		return null;
	}

	private String generateToken(MultiValueMap<String, String> headers, UserEntity userEntity) {
		final Map<String, Object> claims = new ConcurrentHashMap<>();
		String userAgent = headers.getFirst("user-agent");
		claims.put("sub", userEntity.getEmail());
		claims.put("user-agent", userAgent);
		claims.put("iat", new Date());
		return Jwts.builder().setSubject(userEntity.getEmail()).setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, authSecret).compact();

	}
}


