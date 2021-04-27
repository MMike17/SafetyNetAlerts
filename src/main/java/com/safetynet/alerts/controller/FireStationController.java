package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to receive requests regarding the FireStations
 * 
 * @see FireStation
 * 
 * @author Mike Matthews
 */
@RestController
public class FireStationController {

	@Autowired
	FireStationService service;

	/**
	 * Method receiving Post requests
	 * 
	 * @return the FireStation object saved and updated
	 */
	@PostMapping("/firestation")
	public FireStation saveStation(FireStation station) {

		throw new NotImplementedException();
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return true if the operation was a success
	 */
	@PutMapping("/firestation")
	public boolean updateStation(FireStation station) {

		throw new NotImplementedException();
	}

	/**
	 * Method receiving Delete requests
	 * 
	 * @return true if the operation was a success
	 */
	@DeleteMapping("/firestation")
	public boolean deleteStation(FireStation station) {

		throw new NotImplementedException();
	}
}