package com.aftab.rating.controller;

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

import com.aftab.rating.entity.Rating;
import com.aftab.rating.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	// create rating
	@PostMapping("/")
	public ResponseEntity<Rating> create(@RequestBody Rating rating) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
	}

	// get All
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getAllRatings() {
		return ResponseEntity.ok(ratingService.getAllRatings());
	}

	// get All by User Id
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(ratingService.getAllByUserId(userId));
	}

	// get All by Hotel Id
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId) {
		return ResponseEntity.ok(ratingService.getAllByHotelId(hotelId));
	}
}
