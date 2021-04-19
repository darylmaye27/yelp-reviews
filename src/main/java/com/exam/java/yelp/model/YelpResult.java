package com.exam.java.yelp.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class YelpResult {
	
	private Business business;
	
	private List<Review> reviews;

}
