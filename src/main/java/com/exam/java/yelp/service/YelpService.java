package com.exam.java.yelp.service;

import com.exam.java.yelp.model.YelpResult;

public interface YelpService {
	
	/**
	 * Retrieves the yelp reviews of a restaurant
	 * 
	 * @param restaurantName
	 * @return YelpResult
	 */
	public YelpResult getReviews(String restaurantName);
	
}
