package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return repository.save(person);
	}

	/**
	 * Updates infos of Person in database
	 * 
	 * @return updated Person object
	 */
	public Person updatePersonProfile(Person person) {

		Optional<Person> dbPerson = repository.findById(person.getId());

		if (!dbPerson.isPresent()) {

			System.out.println("Didn't find any object with Id " + person.getId() + " in database");
			return null;
		}

		return repository.save(person);
	}

	/**
	 * Deletes Person object in database
	 * 
	 * @param firstName first name of the Person object
	 * @param lastName  last name of the Person object
	 * @return true if the operation was a success
	 */
	public boolean removePerson(final String firstName, final String lastName) {

		List<Person> dbPersons = repository.findByFirstNameAndLastName(firstName, lastName);

		if (dbPersons == null || dbPersons.size() <= 0) {

			System.out.println("Didn't find any Person with first name : " + firstName + " and last name : " + lastName
					+ " in database");
			return false;
		}

		repository.deleteById(dbPersons.get(0).getId());
		return true;
	}

	/**
	 * Get list of people at the provided adress
	 */
	public ArrayList<Person> getPeopleAtAddress(String address) {

		ArrayList<Person> selectedPeople = new ArrayList<Person>();

		List<Person> people = repository.findByAddress(address);
		selectedPeople.addAll(people);

		return selectedPeople;
	}

	/**
	 * Get list of people from the provided first and last name
	 */
	public ArrayList<Person> getPeopleFromName(String firstName, String lastName) {

		ArrayList<Person> selectedPeople = new ArrayList<Person>();

		List<Person> people = repository.findByFirstNameAndLastName(firstName, lastName);

		if (people == null || people.size() <= 0) {

			System.out.println("Couldn't find people with first name " + firstName + " and last name " + lastName
					+ " in database");
			return null;
		}

		selectedPeople.addAll(people);

		return selectedPeople;
	}

	/**
	 * Get list of people from the provided city name
	 */
	public ArrayList<Person> getPeopleFromCity(String cityName) {

		ArrayList<Person> selectedPeople = new ArrayList<Person>();

		List<Person> people = repository.findByCity(cityName);

		if (people == null || people.size() <= 0) {

			System.out.println("Couldn't find people living in " + cityName + " in database");
			return null;
		}

		boolean isGoodResult = selectedPeople.addAll(people);

		// Backup code if normal code failed
		if (!isGoodResult) {

			for (Person person : people)
				selectedPeople.add(person);
		}

		return selectedPeople;
	}
}