package com.aftab.rating.services;

import java.util.List;

import com.aftab.rating.entity.Rating;

public interface RatingService {

	// create
	Rating create(Rating rating);

	// get All Rating
	List<Rating> getAllRatings();

	// get All By UserId
	List<Rating> getAllByUserId(String userId);

	// get All By Hotel;
	List<Rating> getAllByHotelId(String hotelId);

}
