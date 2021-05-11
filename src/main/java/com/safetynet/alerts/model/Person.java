package com.safetynet.alerts.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
/**
 * Class used to model the Person object in database
 * 
 * @author MikeMatthews
 */
public class Person {

	public Person() {

		firstName = null;
		lastName = null;
	}

	public Person(String firstName, String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "first_name")
	final String firstName;

	@Column(name = "last_name")
	final String lastName;

	@Column(name = "address")
	String address;

	@Column(name = "city")
	String city;

	@Column(name = "zip")
	Integer zipCode;

	@Column(name = "phone")
	String phone;

	@Column(name = "email")
	String email;

	/**
	 * Returns true if the object doesn't have null important fields
	 */
	@JsonIgnore
	public boolean isValid() {

		if (firstName == null || firstName == "")
			return false;

		if (lastName == null || lastName == "")
			return false;

		if (address == null || address == "")
			return false;

		if (city == null || city == "")
			return false;

		if (zipCode <= 0)
			return false;

		if (phone == null || phone == "")
			return false;

		if (email == null || email == "")
			return false;

		return true;
	}
}