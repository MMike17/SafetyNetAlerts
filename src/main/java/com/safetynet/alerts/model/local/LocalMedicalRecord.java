package com.safetynet.alerts.model.local;

/**
 * Class modeling medical record from local data file
 * 
 * @see com.safetynet.alerts.controller.LocalDataReader
 * @author MikeMatthews
 */
public class LocalMedicalRecord {
	public String firstName;
	public String lastName;
	public String birthdate;
	public String[] medications;
	public String[] allergies;
}