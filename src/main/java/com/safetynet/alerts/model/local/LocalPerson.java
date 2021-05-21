package com.safetynet.alerts.model.local;

import com.safetynet.alerts.model.Person;

/**
 * Class modeling a person from local data file
 * 
 * @see LocalDataReader
 * @see Person
 * 
 * @author MikeMatthews
 */
public class LocalPerson {

	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String zip;
	public String phone;
	public String email;

	public Person toPerson() {

		Person person = new Person(firstName, lastName);

		person.setAddress(address);
		person.setCity(city);
		person.setZipCode(Integer.parseInt(zip));
		person.setPhone(phone);
		person.setEmail(email);

		return person;
	}
}