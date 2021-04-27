package com.example.demo.repositories;

import com.example.demo.entity.AccessToken;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AccessTokenRepository  extends PagingAndSortingRepository<AccessToken, String> {

	Optional<AccessToken> findAccessTokenByUsername(String username);

	Optional<AccessToken> findAccessTokenByToken( String token);
}
