package com.safetynet.alerts.service;

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
	 * @return true if the operation was a success
	 */
	public boolean addPerson(Person person) {
		try {
			repository.save(person);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception);
			return false;
		}

		return true;
	}

	/**
	 * Updates infos of Person in database
	 * 
	 * @return true if the operation was a success
	 */
	public boolean updatePersonProfile(Person person) {
		try {
			repository.save(person);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception);
			return false;
		}

		return true;
	}

	/**
	 * Deletes Person object in database
	 * 
	 * @param id ID of the Person object
	 * @return true if the operation was a success
	 */
	public boolean removePerson(final Long id) {
		try {
			repository.deleteById(id);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception);
			return false;
		}

		return true;
	}
}