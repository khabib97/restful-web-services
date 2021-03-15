package com.old.school.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/filtered-user")
	public User getUser() {
		return new User("khabib97","1013ssew");
	}
	
	@GetMapping("/filtered-user-list")
	public List<User> getAllUser() {
		return Arrays.asList(new User("khabib97","1013ssew"),new User("sonet","errsew")) ;
	}
}
