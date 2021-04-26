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
	 * @param firstName first name of the Person object
	 * @param lastName  last name of the Person object
	 * @return true if the operation was a success
	 */
	public boolean removePerson(final String firstName, final String lastName) {

		Iterable<Person> dbPersons = repository.findAll();
		Person selectedPerson = null;

		for (Person person : dbPersons) {
			if (person.getFirstName() == firstName || person.getLastName() == lastName) {
				selectedPerson = person;
				break;
			}
		}

		if (selectedPerson == null) {
			System.out.println("Didn't find any Person with first name : " + firstName + " and last name : " + lastName
					+ " in database");
			return false;
		}

		repository.deleteById(selectedPerson.getId());
		return true;
	}
}