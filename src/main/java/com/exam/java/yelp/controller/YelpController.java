package com.exam.java.yelp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.java.yelp.model.YelpResult;
import com.exam.java.yelp.service.YelpService;

@RestController
public class YelpController {
	
	Logger LOG = LoggerFactory.getLogger(YelpController.class);
	
	@Autowired
	private YelpService yelpService;
	
	@GetMapping("/get-reviews")
	public ResponseEntity<YelpResult> getReviews(@RequestParam(value="restaurant", required = true) String restaurantName) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(yelpService.getReviews(restaurantName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
