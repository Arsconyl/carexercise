package com.ptc.carexercise.car;

import com.ptc.carexercise.person.Person;
import lombok.Data;

@Data
public class CarDTO {

	private Long id;

	private String brand;

	private Long numberOfSeats;

	private Color color;

	private Person person;
}
