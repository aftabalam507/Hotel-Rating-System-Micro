package com.aftab.service;

import java.util.List;

import com.aftab.entity.User;

public interface UserService {

	// create
	User createUser(User user);

	// get all user
	List<User> getAllUser();

	// get single user
	User getUser(String userId);

	// delete user
	void deleteUser(String userId);

	// update user
	User updateUser(User user, String userId);

}
