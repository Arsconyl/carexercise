package com.ptc.carexercise.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonNotFoundAdvice {

	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(value= HttpStatus.NOT_FOUND)
	public String personNotFoundHandler(PersonNotFoundException ex) {
		return ex.getMessage();
	}
}
