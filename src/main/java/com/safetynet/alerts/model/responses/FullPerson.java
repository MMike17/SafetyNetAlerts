package com.safetynet.alerts.model.responses;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * Class used to model a full person (Person, age, MedicalRecord)
 * 
 * @see Person
 * @see MedicalRecord
 * 
 * @author Mike Matthews
 */
public class FullPerson {

	Person person;
	MedicalRecord medicalRecord;
	int age;

	public FullPerson(Person person, MedicalRecord medicalRecord, int age) {

		this.person = person;
		this.medicalRecord = medicalRecord;
		this.age = age;
	}

	public FullPerson() {

	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean compare(FullPerson other) {

		if (!person.compare(other.getPerson()))
			return false;

		if (medicalRecord != null && other.getMedicalRecord() != null
				&& !medicalRecord.compare(other.getMedicalRecord()))
			return false;

		if (age != other.getAge())
			return false;

		return true;
	}
}