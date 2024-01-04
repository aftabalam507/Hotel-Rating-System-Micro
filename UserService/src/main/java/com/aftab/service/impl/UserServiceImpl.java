package com.aftab.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aftab.entity.User;
import com.aftab.exception.ResourceNotFoundException;
import com.aftab.repository.UserRepo;
import com.aftab.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User createUser(User user) {
		// generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return this.userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {

		return this.userRepo.findAll();
	}

	@Override
	public User getUser(String userId) {

		return this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not find !! : " + userId));

	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User updateUser(User user, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
