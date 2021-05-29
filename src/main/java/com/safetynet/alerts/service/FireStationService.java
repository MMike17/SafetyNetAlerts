package com.safetynet.alerts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.repository.FireStationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that returns FireStation objects
 * 
 * @see FireStation
 * 
 * @author MikeMatthews
 */
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
		return repository.save(station);
	}

	/**
	 * Updates infos of FireStation in database
	 * 
	 * @return updated MedicalRecord object
	 */
	public FireStation updateFireStation(FireStation station) {

		Optional<FireStation> dbStation = repository.findById(station.getId());

		if (!dbStation.isPresent()) {

			System.out.println("Didn't find any object with Id " + station.getId() + " in database");
			return null;
		}

		return repository.save(station);
	}

	/**
	 * Deletes FireStation object in database
	 */
	public void removeFireStation(final Long id) {
		repository.deleteById(id);
	}

	/**
	 * Gets list of addresses of station with provided ID
	 * 
	 * @return a list of addresses, null if the list is empty
	 */
	public ArrayList<String> getAddressesFromStationID(final Integer stationID) {

		ArrayList<String> addresses = new ArrayList<String>();

		List<FireStation> stations = repository.findByStationId(stationID);

		if (stations == null || stations.size() <= 0)
			return null;

		for (FireStation station : stations)
			addresses.add(station.getAddress());

		return addresses;
	}

	/**
	 * Gets station Id from the provided address
	 */
	public Integer getStationIDFromAddress(final String address) {

		List<FireStation> stations = repository.findByAddress(address);

		if (stations == null || stations.size() <= 0)
			return -1;

		for (FireStation station : stations)
			return station.getStationId();

		return -1;
	}
}