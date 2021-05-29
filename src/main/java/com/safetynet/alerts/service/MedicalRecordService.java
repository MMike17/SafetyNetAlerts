package com.safetynet.alerts.service;

import java.util.List;
import java.util.Optional;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that returns MedicalRecord objects
 * 
 * @see MedicalRecord
 * 
 * @author MikeMatthews
 */
@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository repository;

	/**
	 * Saves MedicalRecord object in database
	 * 
	 * @return MedicalRecord with ID updated from database
	 */
	public MedicalRecord addRecord(MedicalRecord record) {

		return repository.save(record);
	}

	/**
	 * Updates infos of MedicalRecord in database
	 * 
	 * @return updated MedicalRecord object
	 */
	public MedicalRecord updateRecord(MedicalRecord record) {

		Optional<MedicalRecord> dbRecord = repository.findById(record.getId());

		if (!dbRecord.isPresent()) {

			System.out.println("Didn't find any object with Id " + record.getId() + " in database");
			return null;
		}

		return repository.save(record);
	}

	/**
	 * Deletes MedicalRecord object in database
	 * 
	 * @param firstName first name of the Person the reconrd is attached to
	 * @param lastName  last name of the Person the reconrd is attached to
	 * @return true if the operation was a success
	 */
	public boolean removeRecord(final String firstName, final String lastName) {

		List<MedicalRecord> dbRecords = repository.findByFirstNameAndLastName(firstName, lastName);

		if (dbRecords == null || dbRecords.size() <= 0) {

			System.out.println("Didn't fin any MedicalRecord with first name : " + firstName + " and last name : "
					+ lastName + " in database");
			return false;
		}

		repository.deleteById(dbRecords.get(0).getId());
		return true;
	}

	/**
	 * Gets the MedicalRecord of the provided Person
	 */
	public MedicalRecord getRecordForPerson(Person person) {

		List<MedicalRecord> records = repository.findByFirstNameAndLastName(person.getFirstName(),
				person.getLastName());

		MedicalRecord record = null;

		if (records != null && records.size() > 0)
			record = records.get(0);
		else {

			System.out.println("Couldn't find medical record for a person with name " + person.getFirstName() + " "
					+ person.getLastName());
		}

		return record;
	}
}