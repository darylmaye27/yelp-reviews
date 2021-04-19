package com.exam.java.yelp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Review implements Serializable {
	
	private static final long serialVersionUID = 1286349952167010104L;

	private String id; 
	
	private String url;
	
	private String text;
	
	private int rating;
	
	private String timeCreated;
	
	private User user;

}
