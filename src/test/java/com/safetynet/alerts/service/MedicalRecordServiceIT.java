package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interactions between PersonService and the database
 * 
 * @see PersonService
 * @author MikeMatthews
 */
@SpringBootTest
public class MedicalRecordServiceIT {

	@Autowired
	MedicalRecordRepository repository;

	@Autowired
	MedicalRecordService testedService;

	static TestDataGenerator dataGenerator;

	@BeforeAll
	static void setUp() {

		dataGenerator = new TestDataGenerator();
	}

	@BeforeEach
	void setUpPerTest() {

		repository.deleteAll();
	}

	/**
	 * Tests if the provided MedicalRecord object is saved in database
	 * 
	 * @see MedicalRecordService#addRecord(MedicalRecord)
	 */
	@Test
	public void testRecordSave() {

		// GIVEN
		MedicalRecord testRecord = dataGenerator.generateTestRecord();

		// WHEN
		testRecord = testedService.addRecord(testRecord);

		// THEN
		Optional<MedicalRecord> resultRecord = repository.findById(testRecord.getId());

		if (!resultRecord.isPresent())
			fail("The test data was not saved, but the repository said the data was saved");

		assertEquals(testRecord, resultRecord.get());
	}

	/**
	 * Tests if the provided MedicalRecord object is updated in database
	 * 
	 * @see MedicalRecordService#updateRecord(MedicalRecord)
	 */
	@Test
	public void testRecordUpdate() {

		// GIVEN
		String[] expectedMedication = new String[] { "doliprane" };
		MedicalRecord testRecord = repository.save(dataGenerator.generateTestRecord());
		testRecord.setMedications(expectedMedication);

		// WHEN
		MedicalRecord dbRecord = testedService.updateRecord(testRecord);

		// THEN
		assertEquals(expectedMedication.length, dbRecord.getMedications().length);
		assertEquals(expectedMedication[0], dbRecord.getMedications()[0]);
	}

	/**
	 * Tests if the provided MedicalRecord object is deleted in database
	 * 
	 * @see MedicalRecordService#removeRecord(String, String)
	 */
	@Test
	public void testRecordDelete() {

		// GIVEN
		MedicalRecord testRecord = repository.save(dataGenerator.generateTestRecord());
		Long expectedCount = repository.count() - 1;
		Optional<MedicalRecord> dbTestRecord = repository.findById(testRecord.getId());

		if (!dbTestRecord.isPresent())
			fail("The repository failed to save data for test");

		// WHEN
		boolean succeeded = testedService.removeRecord(testRecord.getFirstName(), testRecord.getLastName());

		// THEN
		if (!succeeded)
			fail("The repository failed to delete the data");

		assertEquals(expectedCount, repository.count());
	}
}