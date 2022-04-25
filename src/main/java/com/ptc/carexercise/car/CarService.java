package com.ptc.carexercise.car;

import org.springframework.stereotype.Service;

@Service
public class CarService {

	private final CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
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
}
