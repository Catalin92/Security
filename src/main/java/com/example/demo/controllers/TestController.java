package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@GetMapping("/test")
	public String test(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info(authentication.getName());
		log.info("Successfully run test path");

		return "test";
	}


	@PostMapping("/post")
	public String postTest(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info(authentication.getName());
		log.info("Successfully run state changing method");

		return "POST";
	}


	@PostMapping("/post2")
	public String postTest2(){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info(authentication.getName());
		log.info("Successfully run state changing method2");

		return "POST2";
	}




}
