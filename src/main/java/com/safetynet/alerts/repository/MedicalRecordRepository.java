package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.MedicalRecord;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to access MedicalRecord objects in database
 * 
 * @see MedicalRecord
 * 
 * @author MikeMatthews
 */
@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

	public List<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName);
}