package com.old.school.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Note:if we use customized/generic exception handler then 
//this @ResponseStatus will be overridden by 
//customized/generic exception's insider HttpStatus status code

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}
}
 