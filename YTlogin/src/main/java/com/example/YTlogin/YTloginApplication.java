package com.example.YTlogin;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;



@SpringBootApplication
@EnableAsync
public class YTloginApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(YTloginApplication.class, args);
	}
    
	
}
