package com.safetynet.alerts.model.local;

import com.safetynet.alerts.model.FireStation;

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

	public FireStation toStation() {

		return new FireStation(address, Integer.valueOf(station));
	}
}