package com.old.school.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
    private UserDaoService userDaoService;
	
	@GetMapping(path="/users")
	public List<User> retriveAllUsers(){
		return userDaoService.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User retriveUser(@PathVariable  int id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
		return user;
	}
	
	// created new user
	// input - details of user
	// output - created & return created user
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		
		// return status of created user
		// build uri
		// created status code : 201 with Location
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUserById(@PathVariable  int id) {
		User user = userDaoService.deleteById(id);
		
		if(user == null) {
			throw new UserNotFoundException("id-"+id);
		}
	}
	
	
	
}
