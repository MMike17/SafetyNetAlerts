package com.safetynet.alerts.model.local;

import java.sql.Date;

import com.safetynet.alerts.model.MedicalRecord;

/**
 * Class modeling medical record from local data file
 * 
 * @see LocalDataReader
 * @see MedicalRecord
 * 
 * @author MikeMatthews
 */
public class LocalMedicalRecord {

	public String firstName;
	public String lastName;
	public String birthdate;
	public String[] medications;
	public String[] allergies;

	public MedicalRecord toRecord() {

		MedicalRecord record = new MedicalRecord(firstName, lastName);

		String month = birthdate.substring(0, 2);
		String day = birthdate.substring(3, 5);
		String year = birthdate.substring(6);

		String convertibleBirthdate = year + "-" + month + "-" + day;
		Date date = Date.valueOf(convertibleBirthdate);

		record.setBirthDate(date);
		record.setMedications(medications);
		record.setAllergies(allergies);

		return record;
	}
}