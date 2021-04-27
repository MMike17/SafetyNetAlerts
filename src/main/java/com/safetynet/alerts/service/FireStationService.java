package com.safetynet.alerts.service;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class FireStationService {

	@Autowired
	private FireStationRepository repository;

	/**
	 * Saves FireStation object in database
	 * 
	 * @return FireStation with ID updated from database
	 */
	public FireStation addFireStation(FireStation station) {

		throw new NotImplementedException();
	}

	/**
	 * Updates infos of FireStation in database
	 * 
	 * @return true if the operation was a success
	 */
	public boolean updateRecord(FireStation station) {

		throw new NotImplementedException();
	}

	/**
	 * Deletes FireStation object in database
	 * 
	 * @return true if the operation was a success
	 */
	public boolean removeFireStation(final Long id) {

		throw new NotImplementedException();
	}
}