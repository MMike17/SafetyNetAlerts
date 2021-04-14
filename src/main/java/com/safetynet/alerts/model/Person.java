package com.safetynet.alerts.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
/**
 * Class used to model the Person object in database
 * @author MikeMatthews
 */
public class Person {
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
	Integer zipCode;

	@Column(name = "phone")
	String phone;

	@Column(name = "email")
	String email;
}