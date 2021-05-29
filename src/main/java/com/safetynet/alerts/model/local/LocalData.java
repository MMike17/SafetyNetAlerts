package com.safetynet.alerts.model.local;

/**
 * Target class for reading local data from json file
 * 
 * @see LocalDataReader
 * @see LocalPerson
 * @see LocalFireStation
 * @see LocalMedicalRecord
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