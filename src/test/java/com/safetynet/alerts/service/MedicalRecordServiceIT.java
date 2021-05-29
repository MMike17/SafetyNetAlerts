package com.safetynet.alerts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
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

		if (!testRecord.compare(resultRecord.get()))
			fail("Test data and result data are not the same");
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

		assertEquals((long) expectedCount, (long) repository.count());
	}

	/**
	 * Tests if the right medial record is returned for valid person
	 * 
	 * @see MedicalRecordService#getRecordForPerson(Person)
	 */
	@Test
	public void testGetRecordForValidPerson() {

		// GIVEN
		MedicalRecord expectedRecord = dataGenerator.generateTestRecord();
		expectedRecord.setFirstName("X");
		expectedRecord = repository.save(expectedRecord);

		repository.save(dataGenerator.generateTestRecord());

		Person testPerson = dataGenerator.generateTestPerson();
		testPerson.setFirstName("X");

		// WHEN
		MedicalRecord resultRecord = testedService.getRecordForPerson(testPerson);

		// THEN
		if (!expectedRecord.compare(resultRecord))
			fail("Test data and result data are not the same");
	}

	/**
	 * Tests if the right medial record is returned for invalid person
	 * 
	 * @see MedicalRecordService#getRecordForPerson(Person)
	 */
	@Test
	public void testGetRecordForInvalidPerson() {

		// GIVEN
		MedicalRecord testRecord = dataGenerator.generateTestRecord();
		testRecord.setFirstName("X");
		repository.save(testRecord);

		repository.save(dataGenerator.generateTestRecord());

		Person testPerson = dataGenerator.generateTestPerson();
		testPerson.setFirstName("YY");

		// WHEN
		MedicalRecord resultRecord = testedService.getRecordForPerson(testPerson);

		// THEN
		if (resultRecord != null)
			fail("Result was supposed to be null but was " + resultRecord);
	}
}