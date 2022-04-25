package com.ptc.carexercise.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ptc.carexercise.car.Car;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="People")
public class Person {

	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Long id;

	@Column(name = "first_name", length = 128, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 128)
	private String lastName;

	@Column(name="company", length = 128, nullable = false)
	private String company;

	@Enumerated(EnumType.ORDINAL)
	private Type type;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Car> cars;
}
