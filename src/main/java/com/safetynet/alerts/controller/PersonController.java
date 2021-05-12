package com.safetynet.alerts.controller;

import com.safetynet.alerts.exceptions.InvalidObjectException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Person savePerson(@RequestBody Person person) {

		if (person.isValid())
			return service.addPerson(person);
		else
			throw new InvalidObjectException();
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return the Person object updated
	 */
	@PutMapping("/person")
	public Person updatePerson(@RequestBody Person person) {

		if (person.isValid())
			return service.updatePersonProfile(person);
		else
			throw new InvalidObjectException();
	}

	/**
	 * Method receiving Delete requests
	 * 
	 * @return true if the operation was a success
	 */
	@DeleteMapping("/person")
	public boolean deletePerson(@RequestBody final String[] names) {

		if (!StringUtils.isBlank(names[0]) && !StringUtils.isBlank(names[1]))
			return service.removePerson(names[0], names[1]);
		else
			throw new InvalidObjectException();
	}
}