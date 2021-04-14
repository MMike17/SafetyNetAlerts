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
		return false;
	}

	/**
	 * Updates infos of Person in database
	 * 
	 * @return true if the operation was a success
	 */
	public boolean updatePersonProfile(Person person) {
		return false;
	}

	/**
	 * Deletes Person object in database
	 * 
	 * @param id ID of the Person Object
	 * @return true if the operation was a success
	 */
	public boolean removePerson(final Long id) {
		return false;
	}
}