package com.safetynet.alerts.service;

import java.util.Optional;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
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

		MedicalRecord saved = repository.save(record);
		return saved;
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

		Iterable<MedicalRecord> dbRecords = repository.findAll();
		MedicalRecord selectedRecord = null;

		for (MedicalRecord record : dbRecords) {
			if (record.getFirstName() == firstName || record.getLastName() == lastName) {
				selectedRecord = record;
				break;
			}
		}

		if (selectedRecord == null) {
			System.out.println("Didn't fin any MedicalRecord with first name : " + firstName + " and last name : "
					+ lastName + "in database");
			return false;
		}

		repository.deleteById(selectedRecord.getId());
		return true;
	}

	/**
	 * Gets the MedicalRecord of the provided Person
	 */
	public MedicalRecord getRecordForPerson(Person person) {

		Iterable<MedicalRecord> records = repository.findAll();

		for (MedicalRecord record : records) {

			boolean same = record.getFirstName() == person.getFirstName()
					&& record.getLastName() == person.getLastName();

			System.out.println("\nfirst name : " + person.getFirstName() + " / " + record.getFirstName()
					+ "\nlast name : " + person.getLastName() + " / " + record.getLastName() + "\nsame ? " + same);

			if (record.getFirstName() == person.getFirstName() && record.getLastName() == person.getLastName())
				return record;
		}

		return null;
	}
}