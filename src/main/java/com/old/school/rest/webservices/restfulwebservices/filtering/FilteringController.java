package com.old.school.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@Autowired
	FilterUtil filterUtil;
	
	@GetMapping("/filtered-user")
	public User getUser() {
		return new User("khabib97","1013ssew", "dffe");
	}
	
	@GetMapping("/filtered-user-list")
	public List<User> getAllUser() {
		return Arrays.asList(new User("khabib97","1013ssew", "dffwe"),new User("sonet","errsew","tpp")) ;
	}
	
	@GetMapping("/dynamic-filtered-user")
	public MappingJacksonValue getDynamicFilteredUser() {
		UserForDynamicFiltering userForDynamicFiltering =  new UserForDynamicFiltering("khabib97","1013ssew", "dffe");  
		
		/*SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(userForDynamicFiltering);
		mapping.setFilters(filters);*/
		return filterUtil.dynamicFilter(userForDynamicFiltering, UserForDynamicFiltering.class, Set.of("username","token"));
	}
	
	@GetMapping("/dynamic-filtered-user-list")
	public MappingJacksonValue  getDynamicFilteredAllUser() {
		List<UserForDynamicFiltering> list = Arrays.asList(new UserForDynamicFiltering("khabib97","1013ssew", "dffwe"),new UserForDynamicFiltering("sonet","errsew","tpp"));
		
		
		/*SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username","token");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);*/
		
		return filterUtil.dynamicFilter(list, UserForDynamicFiltering.class, Set.of("username"));
	}
	
	
}
