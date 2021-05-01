package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import org.apache.commons.lang.StringUtils;
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

		if (person != null)
			return service.addPerson(person);
		else
			throw new IllegalArgumentException("The provided object was null");
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return the Person object updated
	 */
	@PutMapping("/person")
	public Person updatePerson(Person person) {

		if (person != null)
			return service.updatePersonProfile(person);
		else
			throw new IllegalArgumentException("The provided object was null");
	}

	/**
	 * Method receiving Delete requests
	 * 
	 * @return true if the operation was a success
	 */
	@DeleteMapping("/person")
	public boolean deletePerson(final String firstName, final String lastName) {

		if (!StringUtils.isBlank(firstName) && !StringUtils.isBlank(lastName))
			return service.removePerson(firstName, lastName);
		else
			throw new IllegalArgumentException("The provided objects was null or blank");
	}
}