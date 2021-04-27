package com.example.demo.security.providers;

import com.example.demo.security.authentication.TokenAuthentication;
import com.example.demo.security.managers.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthProvider  implements AuthenticationProvider {

	@Autowired
	private TokenManager tokenManager;

	private static final Logger log = LoggerFactory.getLogger(TokenAuthProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("TokenAuthProvider");
		String token = authentication.getName();
		boolean exists = tokenManager.contains(token);

		if (exists) {
			return new TokenAuthentication(token, null, null);
		}

		throw new BadCredentialsException(":(");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return TokenAuthentication.class.equals(aClass);
	}
}
