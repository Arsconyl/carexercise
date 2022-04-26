package com.ptc.carexercise.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BearerNotFoundAdvice {

	public static final String BEARER_NOT_FOUND_MESSAGE = "Bearer String not found";

	@ExceptionHandler(BearerNotFoundException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public String bearerNotFoundExceptionHandler() {
		return BEARER_NOT_FOUND_MESSAGE;
	}
}
