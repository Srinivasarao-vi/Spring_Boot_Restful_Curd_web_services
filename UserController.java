package com.restful.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.exception.ResourceNotFoundException;
import com.restful.model.User;
import com.restful.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static final String userId = null;
	@Autowired
	private UserRepository userrepository;

	// get all users
	@GetMapping
	public List<User> getAllUser() {

		return userrepository.findAll();
	}

	// get user By id
	@GetMapping("/{id}")
	public User getUsreById(@PathVariable(value = "id") Long Userid) {

		return this.userrepository.findById(Userid)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + Userid));

	}

	// create user
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {

		return this.userrepository.save(user);

	}
	// update user by id
	@PutMapping("/{id}")
	public User updateUserById(@Valid @RequestBody User user,@PathVariable(value = "id") long userId) {
		
		User CurrendUser=this.userrepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + userId));
		
		CurrendUser.setFirstName(user.getFirstName());
		CurrendUser.setLastName(user.getLastName());
		CurrendUser.setEmail(user.getEmail());
		
		return this.userrepository.save(CurrendUser);
		
		
		
	}
	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		
		User CurrendUser=this.userrepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :: " + userId));
		this.userrepository.delete(CurrendUser);
		
		return ResponseEntity.ok().build();
		
	}
	

}
