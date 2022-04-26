package com.ptc.carexercise.person;

import com.ptc.carexercise.car.Car;
import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {

	private Long id;

	private String firstName;

	private String lastName;

	private String company;

	private Type type;

	private List<Car> cars;
}
