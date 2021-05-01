package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to receive requests regarding MedicalRecords
 * 
 * @see MedicalRecordService
 * 
 * @author Mike Matthews
 */
@RestController
public class MedicalRecordController {

	@Autowired
	MedicalRecordService service;

	/**
	 * Method receiving Post requests
	 * 
	 * @return the MedicalRecord object saved and updated
	 */
	@PostMapping("/medicalRecord")
	public MedicalRecord saveRecord(MedicalRecord record) {

		if (record != null) {
			return service.addRecord(record);
		} else {
			throw new IllegalArgumentException("The provided object was null");
		}
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return the MedicalRecord object updated
	 */
	@PutMapping("/medicalRecord")
	public MedicalRecord updateRecord(MedicalRecord record) {

		if (record != null) {
			return service.updateRecord(record);
		} else {
			throw new IllegalArgumentException("The provided object was null");
		}
	}

	/**
	 * Method receiving Delete requests
	 * 
	 * @return true of the operation was a success
	 */
	@DeleteMapping("/medicalRecord")
	public boolean deleteRecord(final String firstName, final String lastName) {

		if (!StringUtils.isBlank(firstName) && !StringUtils.isBlank(lastName)) {
			return service.removeRecord(firstName, lastName);
		} else {
			throw new IllegalArgumentException("The provided objects was null or blank");
		}
	}
}