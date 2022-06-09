package com.example.YTlogin.Security;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.YTlogin.Entity.DeviceDetailsEntity;
import com.example.YTlogin.Repository.DeviceDetRepository;
import com.example.YTlogin.Security.MutableRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthFilter extends BasicAuthenticationFilter {
	
	private transient final Environment environment;
	
    private DeviceDetRepository deviceDetRepository;

	public AuthFilter(final AuthenticationManager authenticationManager, final Environment environment , DeviceDetRepository deviceDetRepository) 
	{
		super(authenticationManager);
		this.environment = environment;
		this.deviceDetRepository=deviceDetRepository;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws IOException, ServletException 
	{

		final String authHeader = request.getHeader(environment.getProperty("auth.token.header.name"));

		final String property = environment.getProperty("auth.token.header.name.prefix");
		if (authHeader == null || property == null || !authHeader.startsWith(property)) {
			chain.doFilter(request, response);
			return;
		}
    
		final UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        HttpServletRequest req = (HttpServletRequest) request;
        MutableRequest mutableRequest = new MutableRequest(req);
        
        final String token = authHeader.replace(property, "");
        final Claims claim = Jwts.parser().setSigningKey(environment.getProperty("auth.token.secret.key"))
                .parseClaimsJws(token).getBody();
        
        if(claim!=null)
        {
           String email = (String) claim.getSubject();
           mutableRequest.putHeader("user-mail", email);
        }
           chain.doFilter(mutableRequest, response);
}


private UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) 
{
	
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
    final String authHeader = request.getHeader(environment.getProperty("auth.token.header.name"));
    final String property = environment.getProperty("auth.token.header.name.prefix");
    if (authHeader != null && property != null) {
         String jwttoken = authHeader.replace(property, "");
         final String token = jwttoken.trim();
        
        
       Integer deviceDetailsEntity = deviceDetRepository.countByToken(token);
       
       if(deviceDetailsEntity>0 && deviceDetailsEntity!=null)
       {

        final Claims claim = Jwts.parser().setSigningKey(environment.getProperty("auth.token.secret.key"))
                .parseClaimsJws(token).getBody();
                
        if (claim.getSubject() != null) {
            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(claim.getSubject(), null,
                    null);
        }
       }
    }

    return usernamePasswordAuthenticationToken;
}
}
