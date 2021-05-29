package com.safetynet.alerts.controller;

import com.safetynet.alerts.exceptions.InvalidObjectException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to receive requests regarding MedicalRecords
 * 
 * @see MedicalRecordService
 * @see MedicalRecord
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
	public MedicalRecord saveRecord(@RequestBody MedicalRecord record) {

		if (record.isValid())
			return service.addRecord(record);
		else
			throw new InvalidObjectException();
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return the MedicalRecord object updated
	 */
	@PutMapping("/medicalRecord")
	public MedicalRecord updateRecord(@RequestBody MedicalRecord record) {

		if (record.isValid())
			return service.updateRecord(record);
		else
			throw new InvalidObjectException();
	}

	/**
	 * Method receiving Delete requests
	 * 
	 * @return true of the operation was a success
	 */
	@DeleteMapping("/medicalRecord")
	public boolean deleteRecord(@RequestBody final String[] names) {

		if (StringUtils.hasText(names[0]) && StringUtils.hasText(names[1]))
			return service.removeRecord(names[0], names[1]);
		else
			throw new InvalidObjectException();
	}
}