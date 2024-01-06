package com.aftab.hotel.entity.services;

import java.util.List;

import com.aftab.hotel.entity.Hotel;

public interface HotelService {
	
	//create hotel
	Hotel create(Hotel hotel);
	
	//get All Hotel
	List<Hotel> getAllHotel();
	
	//get Single Hotel;
	Hotel getSingleHotel(String Id);
}
