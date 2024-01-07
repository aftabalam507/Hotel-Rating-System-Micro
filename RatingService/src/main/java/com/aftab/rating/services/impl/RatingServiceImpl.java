package com.aftab.rating.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aftab.rating.entity.Rating;
import com.aftab.rating.repository.RatingRepository;
import com.aftab.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating create(Rating rating) {

		// generate unique userId
		String randomRatingId = UUID.randomUUID().toString();
		rating.setRatingId(randomRatingId);

		return this.ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {

		return this.ratingRepository.findAll();
	}

	@Override
	public List<Rating> getAllByUserId(String userId) {

		return this.ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getAllByHotelId(String hotelId) {

		return this.ratingRepository.findByHotelId(hotelId);
	}

}
