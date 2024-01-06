package com.aftab.hotel.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aftab.hotel.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<Map<String, String>> notFoundHandler(ResourceNotFoundException exception) {
//		
//		Map map=new HashMap<>();
//		map.put("message",exception.getMessage());
//		map.put("success", false);
//		map.put("status", HttpStatus.NOT_FOUND);
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
//	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception) {

		String message = exception.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND)
				.build();

		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
}
