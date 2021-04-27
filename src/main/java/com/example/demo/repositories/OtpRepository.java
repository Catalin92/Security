package com.example.demo.repositories;

import com.example.demo.entity.Otp;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OtpRepository extends PagingAndSortingRepository<Otp, String> {
	Optional<Otp> findOtpByUsername(String username);
}


