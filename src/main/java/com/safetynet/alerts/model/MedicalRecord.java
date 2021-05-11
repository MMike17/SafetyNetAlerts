package com.safetynet.alerts.model;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "medicalRecord")
/**
 * Class used to model the MedicalRecord object in database
 * 
 * @author MikeMatthews
 */
public class MedicalRecord {

	public MedicalRecord() {

	}

	public MedicalRecord(String firstName, String lastName) {

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

	@Column(name = "birth_date")
	Date birthDate;

	@Column(name = "medications")
	String[] medications;

	@Column(name = "allergies")
	String[] allergies;

	/**
	 * Returns true if the object doesn't have null important fields
	 */
	@JsonIgnore
	public boolean isValid() {

		if (firstName == null || firstName == "")
			return false;

		if (lastName == null || lastName == "")
			return false;

		if (birthDate == null)
			return false;

		return true;
	}
}