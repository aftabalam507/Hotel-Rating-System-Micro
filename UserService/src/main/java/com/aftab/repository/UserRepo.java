package com.aftab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftab.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

}
