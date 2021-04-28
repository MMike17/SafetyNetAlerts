package com.safetynet.alerts.service;

import java.util.Optional;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;

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

		FireStation saved = repository.save(station);
		return saved;
	}

	/**
	 * Updates infos of FireStation in database
	 * 
	 * @return true if the operation was a success
	 */
	public boolean updateFireStation(FireStation station) {

		Optional<FireStation> dbStation = repository.findById(station.getId());

		if (!dbStation.isPresent()) {
			System.out.println("Didn't find any object with Id " + station.getId() + " in database");
			return false;
		}

		repository.save(station);
		return true;
	}

	/**
	 * Deletes FireStation object in database
	 */
	public void removeFireStation(final Long id) {

		repository.deleteById(id);
	}
}