package com.old.school.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.old.school.rest.webservices.restfulwebservices.user.post.Post;
import com.old.school.rest.webservices.restfulwebservices.user.post.PostRepository;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	

	@GetMapping(path = "/jpa/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		// HATEOAS - Hypermedia as the engine of application state
		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-users "));
		
		return resource;
	}

	// created new user
	// input - details of user
	// output - created & return created user
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		// return status of created user
		// build uri
		// created status code : 201 with Location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);

		// return status of created user
		// build uri
		// created status code : 201 with Location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	
}
