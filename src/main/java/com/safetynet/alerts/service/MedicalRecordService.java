package com.safetynet.alerts.service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository repository;

	public MedicalRecord addRecord(MedicalRecord record) {

		throw new NotImplementedException();
	}

	public boolean updateMedicalRecord(MedicalRecord record) {

		throw new NotImplementedException();
	}

	public boolean removeMedicalRecord(final Long id) {

		throw new NotImplementedException();
	}
}