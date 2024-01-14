package com.aftab.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aftab.entity.Rating;
import com.aftab.entity.User;
import com.aftab.exception.ResourceNotFoundException;
import com.aftab.repository.UserRepo;
import com.aftab.service.UserService;

import ch.qos.logback.classic.Logger;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private org.slf4j.Logger logger=LoggerFactory.getLogger(UserService.class);

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

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not find !! : " + userId));
		//fetching rating from rating service
		//http://localhost:8083/ratings/user/80c56697-2b63-4b55-b5c1-57de9d549d7a
		
		ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/user/"+user.getUserId(), ArrayList.class);
		logger.info("{} ",ratingsOfUser);
		user.setRatings(ratingsOfUser);
		return user;
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
