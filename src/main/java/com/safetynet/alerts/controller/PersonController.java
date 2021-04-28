package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to receive requests regarding the Person object
 * 
 * @see PersonService
 * 
 * @author Mike Matthews
 */
@RestController
public class PersonController {

	@Autowired
	PersonService service;

	/**
	 * Method receiving Post requests
	 * 
	 * @return the Person object saved and updated
	 */
	@PostMapping("/person")
	public Person savePerson(Person person) {

		return service.addPerson(person);
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return true if the operation was a success
	 */
	@PutMapping("/person")
	public boolean updatePerson(Person person) {

		return service.updatePersonProfile(person);
	}

	/**
	 * Method receiving Delete requests
	 * 
	 * @return true if the operation was a success
	 */
	@DeleteMapping("/person")
	public boolean deletePerson(Person person) {

		return service.removePerson(person.getFirstName(), person.getLastName());
	}
}