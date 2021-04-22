package com.safetynet.alerts.service;

import java.util.Optional;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	/**
	 * Saves Person object in database
	 * 
	 * @return Person with ID updated from database
	 */
	public Person addPerson(Person person) {

		Person saved = repository.save(person);
		return saved;
	}

	/**
	 * Updates infos of Person in database
	 * 
	 * @return true if the operation was a success
	 */
	public boolean updatePersonProfile(Person person) {

		Optional<Person> dbPerson = repository.findById(person.getId());

		if (!dbPerson.isPresent()) {
			System.out.println("Didn't find any object with Id " + person.getId() + " in database");
			return false;
		}

		repository.save(person);
		return true;
	}

	/**
	 * Deletes Person object in database
	 * 
	 * @param id ID of the Person object
	 * @return true if the operation was a success
	 */
	public boolean removePerson(final Long id) {

		Optional<Person> dbPerson = repository.findById(id);

		if (!dbPerson.isPresent()) {
			System.out.println("Didn't find any object with Id " + id + " in database");
			return false;
		}

		repository.deleteById(id);
		return true;
	}
}