package io.vishal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MovieCatalogServiceApplication {

	/**
	 * anybody autowires rest template will get this one
	 * @return
	 * bean is producer and autowired is consumer
	 */
	@Bean
	@LoadBalanced //URL which is given is a hint which service needs to be discovered
	public RestTemplate getRestTempleate() {
		return new RestTemplate();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
