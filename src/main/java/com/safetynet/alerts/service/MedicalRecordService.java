package com.safetynet.alerts.service;

import java.util.Optional;

import com.safetynet.alerts.model.MedicalRecord;
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
	 * @return true if the operation was a success
	 */
	public boolean updateRecord(MedicalRecord record) {

		Optional<MedicalRecord> dbRecord = repository.findById(record.getId());

		if (!dbRecord.isPresent()) {
			System.out.println("Didn't find any object with Id " + record.getId() + " in database");
			return false;
		}

		repository.save(record);
		return true;
	}

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
}