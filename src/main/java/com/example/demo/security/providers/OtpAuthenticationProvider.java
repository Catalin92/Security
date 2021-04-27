package com.example.demo.security.providers;

import com.example.demo.entity.Otp;
import com.example.demo.repositories.OtpRepository;
import com.example.demo.security.authentication.OtpAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private OtpRepository otpRepository;

	private static final Logger log = LoggerFactory.getLogger(OtpAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("OtpAuthenticationProvider");
		String username = authentication.getName();
		String otp = (String) authentication.getCredentials();

		Optional<Otp> o = otpRepository.findOtpByUsername(username);

		if (o.isPresent()) {
			return new OtpAuthentication(username, otp, null);
		}

		throw new BadCredentialsException(":(");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return  OtpAuthentication.class.equals(aClass);
	}
}
