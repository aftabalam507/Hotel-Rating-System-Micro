package com.aftab.hotel.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftab.hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
