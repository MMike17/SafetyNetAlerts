package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to access MedicalRecord objects in database
 * 
 * @author MikeMatthews
 * @see MedicalRecord
 */
@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

	public List<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName);
}