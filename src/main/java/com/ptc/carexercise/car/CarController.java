package com.ptc.carexercise.car;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("")
	@ResponseBody
	public Iterable<CarDTO> getCars() {
		Iterable<Car> cars = carService.getAllCars();
		List<Car> carsList = StreamSupport.stream(cars.spliterator(), false).toList();
		return carsList.stream().map(this::convertToDTO).toList();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CarDTO getCar(@PathVariable Long id) {
		return convertToDTO(carService.getCar(id));
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public CarDTO addCar(@RequestBody CarDTO carDto) {
		Car car = convertToEntity(carDto);
		return convertToDTO(carService.createCar(car));
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDto) {
		Car car = convertToEntity(carDto);
		car.setId(id);
		return convertToDTO(carService.updateCar(car));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable Long id) {
		try {
			carService.deleteCar(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private CarDTO convertToDTO(Car car) {
		return modelMapper.map(car, CarDTO.class);
	}

	private Car convertToEntity(CarDTO carDTO) {
		return modelMapper.map(carDTO, Car.class);
	}

}
