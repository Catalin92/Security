package com.example.demo.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


// Usually we implement Authentication directly
public class TokenAuthentication extends UsernamePasswordAuthenticationToken {
	public TokenAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public TokenAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
