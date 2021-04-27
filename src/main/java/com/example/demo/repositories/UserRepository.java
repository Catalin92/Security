package com.example.demo.repositories;

import com.example.demo.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

	Optional<User> findUserByUsername(String username);
}
