package com.exam.java.yelp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class APIUtil {
	
	@Value("${YELP_API_KEY}")
	private String yelpAPIKey;
	
	private static String YELP_API_KEY;
	
	@Value("${API_END_POINT}")
	private String APIEndpoint;
	
	private static String API_ENDPOINT;
	
	private static final String SEARCH = "/businesses/search?term={term}&location=Manila";
	private static final String REVIEWS = "/businesses/{id}/reviews";
	
	@Value("${YELP_API_KEY}")
	public void setYelpAPIKey(String apiKey) {
		APIUtil.YELP_API_KEY = apiKey;
	}
	
	@Value("${API_END_POINT}")
	public void setAPIEndpoint(String apiEndpoint) {
		APIUtil.API_ENDPOINT = apiEndpoint;
	}
	
	public static String getSearchEndpoint() {
		return API_ENDPOINT + SEARCH;
	}
	
	public static String getReviews() {
		return API_ENDPOINT + REVIEWS;
	}
	
	public static HttpEntity<String> getHeaders() {
		return new HttpEntity<String>(new HttpHeaders() {{
	         String authHeader = "Bearer " + new String( YELP_API_KEY );
	         set( "Authorization", authHeader );
	      }});
	}

}
