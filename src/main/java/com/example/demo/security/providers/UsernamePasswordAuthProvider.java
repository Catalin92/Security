package com.example.demo.security.providers;

import com.example.demo.security.authentication.UsernamePasswordAuthentication;
import com.example.demo.services.JPAUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

	@Autowired
	private JPAUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(UsernamePasswordAuthProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.info("UsernamePasswordAuthProvider");
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetails user = userDetailsService.loadUserByUsername(username);
		if (passwordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthentication(username, password, user.getAuthorities());
		}

		throw new BadCredentialsException(":(");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return UsernamePasswordAuthentication.class.equals(aClass);
	}
}
