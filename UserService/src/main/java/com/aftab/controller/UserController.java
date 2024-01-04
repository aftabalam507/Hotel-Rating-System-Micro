package com.aftab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftab.entity.User;
import com.aftab.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// create user

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User createdUser = this.userService.createUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

	}

	// get single user
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {

		User user = this.userService.getUser(userId);

		return ResponseEntity.ok(user);
	}

	// get all user
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser() {
		
		List<User> allUser = this.userService.getAllUser();

		return ResponseEntity.ok(allUser);
	}
}
