package com.ptc.carexercise.person;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}

	public Person getPerson(Long id) {
		return personRepository.findById(id).orElse(null);
	}

	public Person createPerson(Person person) {
		return personRepository.save(person);
	}

	public Person updatePerson(Person person) {
		return personRepository.save(person);
	}

	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
}
