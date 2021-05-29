package com.safetynet.alerts.controller;

import com.safetynet.alerts.exceptions.InvalidObjectException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.service.FireStationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to receive requests regarding the FireStations
 * 
 * @see FireStationService
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
	public FireStation saveStation(@RequestBody FireStation station) {

		if (station.isValid())
			return service.addFireStation(station);
		else
			throw new InvalidObjectException();
	}

	/**
	 * Method receiving Put requests
	 * 
	 * @return the FireStation object updated
	 */
	@PutMapping("/firestation")
	public FireStation updateStation(@RequestBody FireStation station) {

		if (station.isValid())
			return service.updateFireStation(station);
		else
			throw new InvalidObjectException();
	}

	/**
	 * Method receiving Delete requests
	 */
	@DeleteMapping("/firestation")
	public void deleteStation(@RequestBody final Long stationID) {

		if (stationID > -1)
			service.removeFireStation(stationID);
		else
			throw new InvalidObjectException();
	}
}