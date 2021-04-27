package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	PersonService service;

	@PostMapping("/person")
	public Person savePerson(Person person) {

		return service.addPerson(person);
	}

	@PutMapping("/person")
	public boolean updatePerson(Person person) {

		return service.updatePersonProfile(person);
	}

	@DeleteMapping("/person")
	public boolean deletePerson(Person person) {

		return service.removePerson(person.getFirstName(), person.getLastName());
	}
}