package com.safetynet.alerts.model.local;

/**
 * Target class for reading local data from json file
 * 
 * @see com.safetynet.alerts.controller.LocalDataReader
 * @see com.safetynet.alerts.model.local.LocalPerson
 * @see com.safetynet.alerts.model.local.LocalFireStation
 * @see com.safetynet.alerts.model.local.LocalMedicalRecord
 * 
 * @author MikeMatthews
 */
public class LocalData {

	public LocalData() {

		persons = new LocalPerson[0];
		firestations = new LocalFireStation[0];
		medicalrecords = new LocalMedicalRecord[0];
	}

	public LocalPerson[] persons;
	public LocalFireStation[] firestations;
	public LocalMedicalRecord[] medicalrecords;
}