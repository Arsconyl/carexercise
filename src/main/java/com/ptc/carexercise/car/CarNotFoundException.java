package com.ptc.carexercise.car;

public class CarNotFoundException extends RuntimeException {
	public CarNotFoundException(Long id) {
		super("Car with id " + id + " not found");
	}
}
