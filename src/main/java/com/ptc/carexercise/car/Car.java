package com.ptc.carexercise.car;

import com.ptc.carexercise.person.Person;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Cars")
public class Car {

	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Long id;

	@Column(name = "brand", length = 128, nullable = false)
	private String brand;

	@Column(name = "number_of_seats", nullable = false)
	private Long numberOfSeats;

	@Enumerated(EnumType.STRING)
	private Color color;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Person person;
}
