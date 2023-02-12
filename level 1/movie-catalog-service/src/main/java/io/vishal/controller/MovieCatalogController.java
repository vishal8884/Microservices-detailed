package io.vishal.controller;

import java.lang.reflect.ParameterizedType; //used when we need to define List class in .class
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Application;

import io.vishal.models.CatalogItem;
import io.vishal.models.Movie;
import io.vishal.models.Rating;
import io.vishal.models.UserRating;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;   //We can do advanced load balancing using this

	/**
	 * Controller needs to return Mono/Flux object to make this API truly Async when using Webclient
	 * @param userId
	 * @return
	 */
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratings/users/"+userId,UserRating.class);  //This calls from eureka server so we hive ratings-data-service host instead of localhost:8082
		List<CatalogItem> catalogItemsList = new ArrayList<>();
		
		
		for(Rating rating : userRating.getUserRating()) {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			catalogItemsList.add(new CatalogItem(movie.getName(), "test2", rating.getRating()));
		}
		
		
		return catalogItemsList;
	}
}
