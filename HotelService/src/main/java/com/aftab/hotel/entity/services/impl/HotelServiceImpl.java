package com.aftab.hotel.entity.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aftab.hotel.entity.Hotel;
import com.aftab.hotel.entity.repository.HotelRepository;
import com.aftab.hotel.entity.services.HotelService;
import com.aftab.hotel.exception.ResourceNotFoundException;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel create(Hotel hotel) {
		
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return this.hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		
		return this.hotelRepository.findAll();
	}

	@Override
	public Hotel getSingleHotel(String Id) {
		
		return this.hotelRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("hotel not found with givent Id"));
	}
	
	
}
