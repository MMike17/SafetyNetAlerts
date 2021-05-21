package com.safetynet.alerts.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.util.StringUtils;

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
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "address")
	String address;

	@Column(name = "city")
	String city;

	@Column(name = "zip")
	int zipCode;

	@Column(name = "phone")
	String phone;

	@Column(name = "email")
	String email;

	/**
	 * Returns true if the object doesn't have null important fields
	 */
	@JsonIgnore
	public boolean isValid() {

		if (!StringUtils.hasText(firstName))
			return false;

		if (!StringUtils.hasText(lastName))
			return false;

		if (!StringUtils.hasText(address))
			return false;

		if (!StringUtils.hasText(city))
			return false;

		if (zipCode <= 0)
			return false;

		if (!StringUtils.hasText(phone))
			return false;

		if (!StringUtils.hasText(email))
			return false;

		return true;
	}

	/**
	 * Returns true if the objects are the same
	 */
	@JsonIgnore
	public boolean compare(Person other) {

		if (!firstName.equals(other.firstName))
			return false;

		if (!lastName.equals(other.lastName))
			return false;

		if (!address.equals(other.address))
			return false;

		if (!city.equals(other.city))
			return false;

		if (zipCode != other.zipCode)
			return false;

		if (!phone.equals(other.phone))
			return false;

		if (!email.equals(other.email))
			return false;

		return true;
	}
}