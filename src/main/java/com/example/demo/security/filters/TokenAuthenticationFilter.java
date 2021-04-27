package com.example.demo.security.filters;

import com.example.demo.security.authentication.TokenAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private AuthenticationManager authenticationManager;

	private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		log.info("Start TokenAuthenticationFilter");
		String token = request.getHeader("Authorization");

		Authentication authentication = new TokenAuthentication(token, null);

		Authentication a = authenticationManager.authenticate(authentication);

		SecurityContextHolder.getContext().setAuthentication(a);
		filterChain.doFilter(request, response);
	}

	//ASTA FILTREAZA REQUESTUL
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/login");
	}

}
