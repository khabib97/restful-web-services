package com.old.school.rest.webservices.restfulwebservices.filtering;

import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Component
public class FilterUtil {

	public <E> MappingJacksonValue dynamicFilter(E fiterData, Class<?> source  , Set<String> fieldsNotFiltered) {
		
		JsonFilter jsonFilter = (JsonFilter) source.getAnnotation(JsonFilter.class);
 		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsNotFiltered);
		FilterProvider filters = new SimpleFilterProvider().addFilter(jsonFilter.value(), filter);

		MappingJacksonValue mapping = new MappingJacksonValue(fiterData);
		mapping.setFilters(filters);
		
		return mapping;
	}

}
