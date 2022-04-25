package com.ptc.carexercise.car;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CarNotFoundAdvice {

	@ExceptionHandler(CarNotFoundException.class)
	@ResponseStatus(value= HttpStatus.NOT_FOUND)
	public String carNotFoundHandler(CarNotFoundException ex) {
		return ex.getMessage();
	}
}
