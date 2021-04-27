package com.safetynet.alerts;

import com.safetynet.alerts.controller.LocalDataReader;
import com.safetynet.alerts.model.local.LocalData;
import com.safetynet.alerts.properties.LocalFileProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication implements CommandLineRunner {

	@Autowired
	LocalFileProperty localFileProperty;

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDataReader localDataReader = new LocalDataReader(localFileProperty.getLocalFileName());
		LocalData data = localDataReader.readFile();

		if (data != null)
			System.out.println("Loaded local data from file");
	}
}