package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.FireStation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to access FireStation objects in database
 * 
 * @author MikeMatthews
 * @see MedicalRecord
 */
@Repository
public interface FireStationRepository extends CrudRepository<FireStation, Long> {

	public List<FireStation> findByStationId(Integer stationId);

	public List<FireStation> findByAddress(String address);
}