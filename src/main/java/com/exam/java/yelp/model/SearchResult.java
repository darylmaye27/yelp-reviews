package com.exam.java.yelp.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResult {
	
	private List<Business> businesses;

}
