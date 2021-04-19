package com.exam.java.yelp.utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class URIUtil {
	
	public static String getURIString(String host, String restaurantName) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
			      .scheme("https")
			      .host(host)
			      .path(restaurantName)
			      .query("osq={keyword}")
			      .buildAndExpand("Restaurants");
		
		return uriComponents.toString();
	}

}
