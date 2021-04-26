package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Date;
import java.util.Optional;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests the interactions between PersonService and the database
 * 
 * @see PersonService
 * @author MikeMatthews
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MedicalRecordServiceIT {

	@Autowired
	MedicalRecordRepository repository;

	@Autowired
	MedicalRecordService testedService;

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
		MedicalRecord testRecord = generateTestRecord();

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
		MedicalRecord testRecord = repository.save(generateTestRecord());
		testRecord.setMedications(expectedMedication);

		// WHEN
		boolean succeeded = testedService.updateRecord(testRecord);

		// THEN
		if (!succeeded)
			fail("The repository failed to update the data");

		Optional<MedicalRecord> resultRecord = repository.findById(testRecord.getId());

		if (!resultRecord.isPresent())
			fail("The test data was not updated but the repository said the data was updated");

		assertEquals(expectedMedication, resultRecord.get().getMedications());
	}

	/**
	 * Tests if the provided MedicalRecord object is deleted in database
	 * 
	 * @see MedicalRecordService#removeRecord(Long)
	 */
	@Test
	public void testRecordDelete() {

		// GIVEN
		MedicalRecord testRecord = repository.save(generateTestRecord());
		Long expectedCount = repository.count() - 1;
		Optional<MedicalRecord> dbTestRecord = repository.findById(testRecord.getId());

		if (!dbTestRecord.isPresent())
			fail("The repository failed to save data for test");

		// WHEN
		boolean succeeded = testedService.removeRecord(testRecord.getId());

		// THEN
		if (!succeeded)
			fail("The repository failed to delete the data");

		assertEquals(expectedCount, repository.count());
	}

	/**
	 * Generates a test MedicalRecord containing dummy data
	 * 
	 * @return a MedicalRecord object with dummy data
	 */
	MedicalRecord generateTestRecord() {

		MedicalRecord testRecord = new MedicalRecord();
		testRecord.setId(Long.valueOf(0));
		testRecord.setFirstName("Test");
		testRecord.setLastName("TEST");
		testRecord.setBirthDate(new Date(645400800000L));
		testRecord.setMedications(new String[] { "test" });
		testRecord.setAllergies(new String[] { "test" });

		return testRecord;
	}
}