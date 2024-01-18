package com.aftab.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aftab.entity.Hotel;
import com.aftab.entity.Rating;
import com.aftab.entity.User;
import com.aftab.exception.ResourceNotFoundException;
import com.aftab.external.services.HotelService;
import com.aftab.repository.UserRepo;
import com.aftab.service.UserService;
import com.netflix.discovery.converters.Auto;

import ch.qos.logback.classic.Logger;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);

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
		// fetching rating from rating service
		// http://localhost:8083/ratings/user/80c56697-2b63-4b55-b5c1-57de9d549d7a

		Rating[] ratingsOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(), Rating[].class);
		logger.info("{} ", ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/d63ea1e8-b22d-42f8-979c-d8652808c519
//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// set the hotel to rating
			rating.setHotel(hotel);
			// return rating
			return rating;

		}).collect(Collectors.toList());

		user.setRatings(ratingList);
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
