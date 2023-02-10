package io.vishal.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.vishal.models.Rating;
import io.vishal.models.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 5);
	}
	

	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		List<Rating> ratingsList = Arrays.asList(new Rating("1", 4),new Rating("2", 7));
		
		return new UserRating(ratingsList);
	}
}
