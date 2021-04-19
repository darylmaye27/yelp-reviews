package com.exam.java.yelp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
	
	private String id;
	
	@JsonProperty("profile_url")
	private String profileUrl;
	
	@JsonProperty("image_url")
	private String imageUrl;
	
	private String name;

}
