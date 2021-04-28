package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

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

		MedicalRecord saved = service.addRecord(record);
		return saved;
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return true of the operation was a success
	 */
	@PutMapping("/medicalRecord")
	public boolean updateRecord(MedicalRecord record) {

		return service.updateRecord(record);
	}

	/**
	 * Method receiving Delete requests
	 * 
	 * @return true of the operation was a success
	 */
	@DeleteMapping("/medicalRecord")
	public boolean deleteRecord(MedicalRecord record) {

		return service.removeRecord(record.getFirstName(), record.getLastName());
	}
}