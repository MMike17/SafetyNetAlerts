package com.safetynet.alerts.model.responses;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

import lombok.Data;

/**
 * Class used to model a full person (Person, age, MedicalRecord)
 * 
 * @see Person
 * @see MedicalRecord
 * 
 * @author Mike Matthews
 */
@Data
public class FullPerson {

	Person person;
	MedicalRecord medicalRecord;
	int age;

	public FullPerson(Person person, MedicalRecord medicalRecord, int age) {

		this.person = person;
		this.medicalRecord = medicalRecord;
		this.age = age;
	}
}