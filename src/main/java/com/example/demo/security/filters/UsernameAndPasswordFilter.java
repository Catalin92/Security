package com.example.demo.security.filters;

import com.example.demo.entity.AccessToken;
import com.example.demo.entity.Otp;
import com.example.demo.repositories.OtpRepository;
import com.example.demo.security.authentication.OtpAuthentication;

import com.example.demo.security.authentication.UsernamePasswordAuthentication;
import com.example.demo.security.managers.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class UsernameAndPasswordFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private TokenManager tokenManager;

	private static final Logger log = LoggerFactory.getLogger(UsernameAndPasswordFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		// Step 1: username & password
		// Step 2: username & otp
		log.info("Start UsernameAndPasswordFilter");

		String username = httpServletRequest.getHeader("username");
		String password =  httpServletRequest.getHeader("password");
		String otp = httpServletRequest.getHeader("otp");

		if(otp == null){
			log.info("Otp missing -> login session");
			//Step 1

			Authentication a = new UsernamePasswordAuthentication(username, password);
			a = authenticationManager.authenticate(a);

			//Generate otp
			String code = String.valueOf(new Random().nextInt(9999) + 1000);

			Otp otpEntity = new Otp();
			otpEntity.setUsername(username);
			otpEntity.setOtp(code);
			otpRepository.save(otpEntity);
			httpServletResponse.setHeader("otp", code);

		} else {
			log.info("Using otp");
			//Step 2
			Authentication a = new OtpAuthentication(username, otp);
			a = authenticationManager.authenticate(a);
			// we issue a token

			String token = UUID.randomUUID().toString();
			tokenManager.add(new AccessToken(username, token));
			httpServletResponse.setHeader("Authorization", token);
		}

	}

	//ASTA FILTREAZA REQUESTUL
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}
}
