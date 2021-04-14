package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.MedicalRecord;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to access MedicalRecord objects in database
 * 
 * @author MikeMatthews
 * @see com.safetynet.alerts.model.MedicalRecord
 */
@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {

}