package com.exam.java.yelp.model;

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
public class Vision {
	
	private String joy;
	
	private String sorrow;
	
	private String anger;
	
	private String surprise;
	
	private String exposed;
	
	private String blurred;
	
	private String headwear;

}
