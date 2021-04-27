package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


		String[] ss = {"asd", "asd2", "123"};
		List<String> lst = Arrays.asList(ss);

		System.out.println(ss);
		System.out.println("\n");
		System.out.println(lst);
	}

}
