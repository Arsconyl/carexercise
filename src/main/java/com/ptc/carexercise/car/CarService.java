package com.ptc.carexercise.car;

import com.ptc.carexercise.person.Person;
import com.ptc.carexercise.person.PersonService;
import org.springframework.stereotype.Service;

@Service
public class CarService {

	private final CarRepository carRepository;

	private final PersonService personService;

	public CarService(CarRepository carRepository, PersonService personService) {
		this.carRepository = carRepository;
		this.personService = personService;
	}

	public Iterable<Car> getAllCars() {
		return carRepository.findAll();
	}

	public Car getCar(Long id) {
		return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
	}

	public Car createCar(Car car) {
		return carRepository.save(car);
	}

	public Car updateCar(Car car) {
		return carRepository.save(car);
	}

	public void deleteCar(Long id) {
		carRepository.deleteById(id);
	}

	public Car giveCar(Long id, Long personId) {
		Car car = getCar(id);
		Person person = personService.getPerson(personId);
		car.setPerson(person);
		return carRepository.save(car);
	}

	public Car takeCar(Long id) {
		Car car = getCar(id);
		car.setPerson(null);
		return carRepository.save(car);
	}
}
