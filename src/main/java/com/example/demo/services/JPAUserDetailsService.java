package com.example.demo.services;

import com.example.demo.security.models.SecurityUser;
import com.example.demo.entity.User;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JPAUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(JPAUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByUsername(s);

		User u = user.orElseThrow(()-> new UsernameNotFoundException("error"));
		log.info("Found the user in DB");
		return new SecurityUser(u);
	}
}
