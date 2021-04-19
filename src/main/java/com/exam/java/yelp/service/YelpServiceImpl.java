package com.exam.java.yelp.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exam.java.yelp.model.Review;
import com.exam.java.yelp.model.ReviewResult;
import com.exam.java.yelp.model.SearchResult;
import com.exam.java.yelp.model.Vision;
import com.exam.java.yelp.model.YelpResult;
import com.exam.java.yelp.utils.APIUtil;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature.Type;

@Service
public class YelpServiceImpl implements YelpService {
	
	Logger LOG = LoggerFactory.getLogger(YelpServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private CloudVisionTemplate cloudVisionTemplate;
	
	@Override
	public YelpResult getReviews(String restaurantName) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("term", restaurantName);
	    
	    ResponseEntity<SearchResult> response = restTemplate.exchange(APIUtil.getSearchEndpoint(), HttpMethod.GET, APIUtil.getHeaders(), SearchResult.class, params);
		if (!response.getBody().getBusinesses().isEmpty()) {
			ResponseEntity<ReviewResult> reviews = getYelpReviews(response.getBody().getBusinesses().get(0).getId());
		    
			ReviewResult reviewResult = reviews.getBody();
			Iterator<Review> reviewIterator = reviewResult.getReviews().iterator();
			while(reviewIterator.hasNext()) {
				Review review = reviewIterator.next();
				FaceAnnotation annotation = annotateImage(review.getUser().getImageUrl());
				if (annotation != null) {
					Vision vision = Vision.builder()
							.sorrow(annotation.getSorrowLikelihood().name())
							.joy(annotation.getJoyLikelihood().name())
							.anger(annotation.getAngerLikelihood().name())
							.surprise(annotation.getSurpriseLikelihood().name())
							.exposed(annotation.getUnderExposedLikelihood().name())
							.blurred(annotation.getBlurredLikelihood().name())
							.headwear(annotation.getHeadwearLikelihood().name()).build();
					review.getUser().setVisionAPI(vision);
				} else {
					review.getUser().setVisionAPI(Vision.builder().build());
				}
			}
			
			return YelpResult.builder().business(response.getBody().getBusinesses().get(0)).reviews(reviewResult.getReviews()).build();
		} else {
			return YelpResult.builder().build();
		}
	}

	private ResponseEntity<ReviewResult> getYelpReviews(String id) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", id);
	    
		return restTemplate.exchange(APIUtil.getReviews(), HttpMethod.GET, APIUtil.getHeaders(), ReviewResult.class, params);
	}
	
	private FaceAnnotation annotateImage(String imageUrl) {
		AnnotateImageResponse response =
			    this.cloudVisionTemplate.analyzeImage(
			        this.resourceLoader.getResource(imageUrl), Type.FACE_DETECTION);
		
		return response.getFaceAnnotationsCount() > 0 ? response.getFaceAnnotations(0) : null;
	}

}
