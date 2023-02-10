package io.vishal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.vishal.models.CatalogItem;
import io.vishal.models.Movie;
import io.vishal.models.Rating;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		List<Rating> ratings = Arrays.asList(new Rating("1", 4),new Rating("2", 7));
		List<CatalogItem> catalogItemsList = new ArrayList<>();
		
		
		for(Rating rating : ratings) {
			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
			catalogItemsList.add(new CatalogItem(movie.getName(), "test2", rating.getRating()));
		}
		
		
		return catalogItemsList;
	}
}
