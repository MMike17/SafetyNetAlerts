package com.safetynet.alerts.model.local;

import com.safetynet.alerts.model.FireStation;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class modeling fire stations from local data file
 * 
 * @see LocalDataReader
 * @see FireStation
 * 
 * @author MikeMatthews
 */
public class LocalFireStation {

	public String address;
	public String station;

	@SuppressFBWarnings
	public FireStation toStation() {

		FireStation generatedStation = new FireStation(address, Integer.valueOf(station));
		return generatedStation;
	}
}