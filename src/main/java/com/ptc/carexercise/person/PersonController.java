package com.ptc.carexercise.person;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/api/people", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("")
	@ResponseBody
	public Iterable<PersonDTO> getPeople() {
		Iterable<Person> persons = personService.getAllPersons();
		List<Person> personsList = StreamSupport.stream(persons.spliterator(), false).toList();
		return personsList.stream().map(this::convertToDTO).toList();
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PersonDTO getPerson(@PathVariable Long id) {
		return convertToDTO(personService.getPerson(id));
	}

	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersonDTO addPerson(@RequestBody PersonDTO personDto) {
		Person person = convertToEntity(personDto);
		return convertToDTO(personService.createPerson(person));
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PersonDTO updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDto) {
		Person person = convertToEntity(personDto);
		person.setId(id);
		return convertToDTO(personService.updatePerson(person));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Long id) {
		try {
			personService.deletePerson(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private PersonDTO convertToDTO(Person person) {
		return modelMapper.map(person, PersonDTO.class);
	}

	private Person convertToEntity(PersonDTO personDTO) {
		return modelMapper.map(personDTO, Person.class);
	}

}
