package com.safetynet.alerts;

import com.safetynet.alerts.controller.FireStationController;
import com.safetynet.alerts.controller.LocalDataReader;
import com.safetynet.alerts.controller.MedicalRecordController;
import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.model.local.LocalData;
import com.safetynet.alerts.model.local.LocalFireStation;
import com.safetynet.alerts.model.local.LocalMedicalRecord;
import com.safetynet.alerts.model.local.LocalPerson;
import com.safetynet.alerts.properties.LocalFileProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication implements CommandLineRunner {

	@Autowired
	LocalFileProperty localFileProperty;

	@Autowired
	PersonController personController;

	@Autowired
	FireStationController fireStationController;

	@Autowired
	MedicalRecordController medicalRecordController;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LocalDataReader localDataReader = new LocalDataReader(localFileProperty.getLocalFileName());
		LocalData data = localDataReader.readFile();

		if (data != null)
			System.out.println("Loaded local data from file");
		else {

			System.out.println("Failed to load local data");
			return;
		}

		for (LocalPerson person : data.persons)
			personController.savePerson(person.toPerson());

		for (LocalFireStation station : data.firestations)
			fireStationController.saveStation(station.toStation());

		for (LocalMedicalRecord record : data.medicalrecords)
			medicalRecordController.saveRecord(record.toRecord());

		System.out.println("Injected local data in database");
	}
}