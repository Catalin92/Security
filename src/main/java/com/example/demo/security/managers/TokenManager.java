package com.example.demo.security.managers;

import com.example.demo.entity.AccessToken;
import com.example.demo.repositories.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenManager {

	@Autowired
	private AccessTokenRepository repository;

	public void add (AccessToken token) {
		repository.save(token);
	}

	public boolean contains(String token) {
		Optional<AccessToken> opt = repository.findAccessTokenByToken(token);
		return opt.isPresent();
	}

}
