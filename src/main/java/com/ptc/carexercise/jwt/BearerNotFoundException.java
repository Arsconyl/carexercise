package com.ptc.carexercise.jwt;

public class BearerNotFoundException extends RuntimeException {

	public BearerNotFoundException(String message) {
		super(message);
	}
}
