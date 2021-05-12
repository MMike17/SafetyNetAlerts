package com.safetynet.alerts.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.TestDataGenerator;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.responses.FireAlert;
import com.safetynet.alerts.model.responses.FullPerson;
import com.safetynet.alerts.model.responses.HouseHold;
import com.safetynet.alerts.model.responses.HouseHoldWithChildren;
import com.safetynet.alerts.model.responses.PeopleCoveredByStation;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Tests CompositeController with simulated web requests
 * 
 * @see CompositeController
 * 
 * @author Mike Matthews
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CompositeControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	PersonRepository personRepo;

	@Autowired
	FireStationRepository stationRepo;

	@Autowired
	MedicalRecordRepository recordRepo;

	TestDataGenerator dataGenerator = new TestDataGenerator();
	ObjectMapper jsonMapper = new ObjectMapper();

	@BeforeEach
	void setUpPerTest() {

		personRepo.deleteAll();
		stationRepo.deleteAll();
		recordRepo.deleteAll();
	}

	/**
	 * Tests Get request on url "/firestation?stationNumber" with valid object
	 * 
	 * @see CompositeController#getPeopleAndStationIDAtAddress(String)
	 * @see PeopleCoveredByStation
	 */
	@Test
	public void testGetPersonsCoveredByValidStation() throws Exception {

		// GIVEN
		PeopleCoveredByStation testData = generatePeopleCoveredByStationAndInjectData("TestAddress1");

		// WHEN
		MvcResult result = mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk()).andReturn();

		String stringResult = result.getResponse().getContentAsString();
		PeopleCoveredByStation resultObject = jsonMapper.readValue(stringResult, PeopleCoveredByStation.class);

		// THEN
		if (!testData.compare(resultObject))
			fail("expected " + testData + " but result was " + resultObject);
	}

	/**
	 * Tests Get request on url "/firestation?stationNumber" with invalid object
	 * 
	 * @see CompositeController#getPeopleAndStationIDAtAddress(String)
	 * @see PersonCoveredByStation
	 */
	@Test
	public void testGetPersonsCoveredByInvalidStation() throws Exception {

		mockMvc.perform(get("/firestation?stationNumber=-1")).andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Get requests on url "/childAlert?address" with valid object
	 * 
	 * @see CompositeController#getChildrenAtAdress(String)
	 * @see HouseHoldWithChildren
	 */
	@Test
	public void testGetChildrenAtValidAddress() throws Exception {

		// GIVEN
		HouseHoldWithChildren testData = generateHouseHoldWithChildrenAndInjectData("TestAddress1");

		// WHEN
		MvcResult result = mockMvc.perform(get("/childAlert?address=TestAddress1")).andExpect(status().isOk())
				.andReturn();

		String resultString = result.getResponse().getContentAsString();
		HouseHoldWithChildren resultObject = jsonMapper.readValue(resultString, HouseHoldWithChildren.class);

		// THEN
		assertEquals(testData, resultObject);
	}

	/**
	 * Tests Get request on url "/childAlert?address" with invalid object
	 * 
	 * @see CompositeController#getChildrenAtAdress(String)
	 * @see HouseHoldWithChildren
	 */
	@Test
	public void testGetChildrenAtInvalidAddress() throws Exception {

		mockMvc.perform(get("/childAlert?adress=")).andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Get request on url "/phoneAlert?firestation" with valid object
	 * 
	 * @see CompositeController#getPhoneNumbersForCoveredPeople(Integer)
	 */
	@Test
	public void testGetPhoneNumbersForCoveredPeopleWithValidStation() throws Exception {

		// GIVEN
		ArrayList<String> expectedPhoneNumbers = generatePhoneNumbersAndInjectData("TestAddress1");

		// WHEN
		MvcResult result = mockMvc.perform(get("/phoneAlert?firestation=1")).andExpect(status().isOk()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		ArrayList<String> resultArray = jsonMapper.readValue(resultString, ArrayList.class);

		// THEN
		assertEquals(expectedPhoneNumbers, resultArray);
	}

	/**
	 * Tests Get request on url "/phoneAlert?firestation" with invalid object
	 * 
	 * @see CompositeController#getPhoneNumbersForCoveredPeople(Integer)
	 */
	@Test
	public void testGetPhoneNumbersForCoveredPeopleWithInvalidStation() throws Exception {

		mockMvc.perform(get("/phoneAlert?firestation=-1")).andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Get request on url "/fire?address" with valid object
	 * 
	 * @see CompositeController#getPeopleAndStationIDAtAddress(String)
	 */
	@Test
	public void testGetPeopleAndStationIDAtValidAddress() throws Exception {

		// GIVEN
		FireAlert expectedFireAlert = generateFireAlertAndInjectData("TestAddress1");

		// WHEN
		MvcResult result = mockMvc.perform(get("/fire?address=TestAddress1")).andExpect(status().isOk()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		FireAlert resultFireAlert = jsonMapper.readValue(resultString, FireAlert.class);

		// THEN
		assertEquals(expectedFireAlert, resultFireAlert);
	}

	/**
	 * Tests Get request on url "/fire?address" with invalid object
	 * 
	 * @see CompositeController#getPeopleAndStationIDAtAddress(String)
	 */
	@Test
	public void testGetPeopleAndStationIDAtInvalidAddress() throws Exception {

		mockMvc.perform(get("/fire?address=")).andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Get request on url "/flood/stations?stations" with valid object
	 * 
	 * @see CompositeController#getHouseHoldsCoveredByStations(Integer[])
	 */
	@Test
	public void testGetHouseHoldsCoveredByValidStations() throws Exception {

		// GIVEN
		ArrayList<HouseHold> expectedHouseHold = generateHouseHoldsAndInjectData("TestAddress1");

		// WHEN
		MvcResult result = mockMvc.perform(get("/flood/stations?stations=1")).andExpect(status().isOk()).andReturn();

		String stringResult = result.getResponse().getContentAsString();
		ArrayList<HouseHold> resultHouseHolds = jsonMapper.readValue(stringResult, ArrayList.class);

		// THEN
		assertEquals(expectedHouseHold, resultHouseHolds);
	}

	/**
	 * Tests Get request on url "/flood/stations?stations" with invalid object
	 * 
	 * @see CompositeController#getHouseHoldsCoveredByStations(Integer[])
	 */
	@Test
	public void testGetHouseHoldsCoveredByInvalidStations() throws Exception {

		mockMvc.perform(get("/flood/stations?stations=-1")).andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Get request on url "/personInfo?firstName&lastName" with valid object
	 * 
	 * @see CompositeController#getFullInfoFromName(String, String)
	 */
	@Test
	public void testGetFullInfoFromValidName() throws Exception {

		// GIVEN
		ArrayList<FullPerson> expectedPeople = generateFullPeopleAndInjectData();

		// WHEN
		MvcResult result = mockMvc.perform(get("/personInfo?firstName=test11&lastName=TEST11"))
				.andExpect(status().isOk()).andReturn();

		String stringResult = result.getResponse().getContentAsString();
		ArrayList<FullPerson> resultPeople = jsonMapper.readValue(stringResult, ArrayList.class);

		// THEN
		assertEquals(expectedPeople, resultPeople);
	}

	/**
	 * Tests Get request on url "/personInfo?firstName&lastName" with invalid object
	 * 
	 * @see CompositeController#getFullInfoFromName(String, String)
	 */
	@Test
	public void testGetFullInfoFromInvalidName() throws Exception {

		mockMvc.perform(get("/personInfo?firstName=&lastName=TEST11")).andExpect(status().isNotAcceptable());
	}

	/**
	 * Tests Get request on url "/communityEmail?city" with valid object
	 * 
	 * @see CompositeController#getEmailsOfPeopleInCity(String)
	 */
	@Test
	public void testGetEmailsOfPeopleInValidCity() throws Exception {

		// GIVEN
		ArrayList<String> expectedEmails = generateEmailsAndInjectData("CITY1");

		// WHEN
		MvcResult result = mockMvc.perform(get("/communityEmail?city=CITY1")).andExpect(status().isOk()).andReturn();

		String stringResult = result.getResponse().getContentAsString();
		ArrayList<String> resultEmails = jsonMapper.readValue(stringResult, ArrayList.class);

		// THEN
		assertEquals(expectedEmails, resultEmails);
	}

	PeopleCoveredByStation generatePeopleCoveredByStationAndInjectData(String checkedAddress) {

		Person[] people = injectTestPeople();
		injectTestRecords(people);
		injectTestStations();

		ArrayList<Person> selectedPeople = new ArrayList<Person>();
		int childrenCount = 0;

		if ("TestAddress1" == checkedAddress) {

			selectedPeople.add(people[0]);
			selectedPeople.add(people[1]);
			selectedPeople.add(people[2]);

			childrenCount = 1;
		}

		if ("TestAddress2" == checkedAddress) {

			selectedPeople.add(people[3]);
			selectedPeople.add(people[4]);
			selectedPeople.add(people[5]);

			childrenCount = 2;
		}

		return new PeopleCoveredByStation(selectedPeople, childrenCount);
	}

	HouseHoldWithChildren generateHouseHoldWithChildrenAndInjectData(String checkedAddress) {

		ArrayList<Person> adults = new ArrayList<Person>();
		ArrayList<FullPerson> children = new ArrayList<FullPerson>();

		Person[] testPeople = injectTestPeople();
		MedicalRecord[] testRecords = injectTestRecords(testPeople);

		if (checkedAddress == "TestAddress1") {

			children.add(new FullPerson(testPeople[1], testRecords[1], 11));

			adults.add(testPeople[0]);
			adults.add(testPeople[2]);
		}

		if (checkedAddress == "TestAddress2") {

			children.add(new FullPerson(testPeople[3], testRecords[3], 16));
			children.add(new FullPerson(testPeople[4], testRecords[4], 3));

			adults.add(testPeople[5]);
		}

		return new HouseHoldWithChildren(checkedAddress, children, adults);
	}

	FireAlert generateFireAlertAndInjectData(String checkedAddress) {

		ArrayList<FullPerson> selectedPeople = new ArrayList<FullPerson>();
		Integer selectedStation = -1;

		Person[] testPeople = injectTestPeople();
		MedicalRecord[] testRecord = injectTestRecords(testPeople);
		injectTestStations();

		if (checkedAddress == "TestAddress1") {

			selectedPeople.add(new FullPerson(testPeople[0], testRecord[0], 21));
			selectedPeople.add(new FullPerson(testPeople[1], testRecord[1], 11));
			selectedPeople.add(new FullPerson(testPeople[2], testRecord[2], 28));

			selectedStation = 0;
		}

		if (checkedAddress == "TestAddress2") {

			selectedPeople.add(new FullPerson(testPeople[3], testRecord[3], 16));
			selectedPeople.add(new FullPerson(testPeople[4], testRecord[4], 3));
			selectedPeople.add(new FullPerson(testPeople[5], testRecord[5], 61));

			selectedStation = 1;
		}

		return new FireAlert(selectedStation, selectedPeople);
	}

	ArrayList<String> generatePhoneNumbersAndInjectData(String checkedAddress) {

		ArrayList<String> phoneNumbers = new ArrayList<String>();

		Person[] testPeople = injectTestPeople();
		injectTestStations();

		if (checkedAddress == "TestAddress1") {

			phoneNumbers.add(testPeople[0].getPhone());
			phoneNumbers.add(testPeople[1].getPhone());
			phoneNumbers.add(testPeople[2].getPhone());
		}

		if (checkedAddress == "TestAddress2") {

			phoneNumbers.add(testPeople[3].getPhone());
			phoneNumbers.add(testPeople[4].getPhone());
			phoneNumbers.add(testPeople[5].getPhone());
		}

		return phoneNumbers;
	}

	ArrayList<HouseHold> generateHouseHoldsAndInjectData(String checkedAddress) {

		ArrayList<HouseHold> houseHolds = new ArrayList<HouseHold>();

		Person[] testPeople = injectTestPeople();
		MedicalRecord[] testRecords = injectTestRecords(testPeople);
		injectTestStations();

		if (checkedAddress == "TestAddress1") {

			ArrayList<FullPerson> inhabitants = new ArrayList<FullPerson>();

			inhabitants.add(new FullPerson(testPeople[0], testRecords[0], 21));
			inhabitants.add(new FullPerson(testPeople[1], testRecords[1], 11));
			inhabitants.add(new FullPerson(testPeople[2], testRecords[2], 28));

			houseHolds.add(new HouseHold(checkedAddress, inhabitants));
		}

		if (checkedAddress == "TestAddress2") {

			ArrayList<FullPerson> inhabitants = new ArrayList<FullPerson>();

			inhabitants.add(new FullPerson(testPeople[3], testRecords[3], 16));
			inhabitants.add(new FullPerson(testPeople[4], testRecords[4], 3));
			inhabitants.add(new FullPerson(testPeople[5], testRecords[5], 61));

			houseHolds.add(new HouseHold(checkedAddress, inhabitants));
		}

		return houseHolds;
	}

	ArrayList<FullPerson> generateFullPeopleAndInjectData() {

		ArrayList<FullPerson> selectedPeople = new ArrayList<FullPerson>();

		Person[] testPeople = injectTestPeople();
		MedicalRecord[] testRecords = injectTestRecords(testPeople);

		selectedPeople.add(new FullPerson(testPeople[0], testRecords[0], 21));

		return selectedPeople;
	}

	ArrayList<String> generateEmailsAndInjectData(String checkedCity) {

		ArrayList<String> selectedEmails = new ArrayList<String>();

		Person[] testPeople = injectTestPeople();

		if (checkedCity == "CITY1") {

			selectedEmails.add(testPeople[0].getEmail());
			selectedEmails.add(testPeople[1].getEmail());
			selectedEmails.add(testPeople[2].getEmail());
		}

		if (checkedCity == "CITY2") {

			selectedEmails.add(testPeople[3].getEmail());
			selectedEmails.add(testPeople[4].getEmail());
			selectedEmails.add(testPeople[5].getEmail());
		}

		return selectedEmails;
	}

	Person[] injectTestPeople() {

		// inject people with different adresses

		Person testPerson11 = dataGenerator.generateTestPerson();
		testPerson11.setFirstName("test11");
		testPerson11.setLastName("TEST11");
		testPerson11.setAddress("TestAddress1");
		testPerson11.setPhone("0011");
		testPerson11.setCity("CITY1");

		Person testPerson12 = dataGenerator.generateTestPerson();
		testPerson11.setFirstName("test12");
		testPerson11.setLastName("TEST12");
		testPerson11.setAddress("TestAddress1");
		testPerson12.setPhone("0012");
		testPerson12.setCity("CITY1");

		Person testPerson13 = dataGenerator.generateTestPerson();
		testPerson13.setFirstName("test13");
		testPerson13.setLastName("TEST13");
		testPerson13.setAddress("TestAddress1");
		testPerson13.setPhone("0013");
		testPerson13.setCity("CITY1");

		Person testPerson21 = dataGenerator.generateTestPerson();
		testPerson21.setFirstName("test21");
		testPerson21.setLastName("TEST21");
		testPerson21.setAddress("TestAddress2");
		testPerson21.setPhone("0021");
		testPerson21.setCity("CITY2");

		Person testPerson22 = dataGenerator.generateTestPerson();
		testPerson22.setFirstName("test22");
		testPerson22.setLastName("TEST22");
		testPerson22.setAddress("TestAddress2");
		testPerson22.setPhone("0022");
		testPerson22.setCity("CITY2");

		Person testPerson23 = dataGenerator.generateTestPerson();
		testPerson23.setFirstName("test23");
		testPerson23.setLastName("TEST23");
		testPerson23.setAddress("TestAddress2");
		testPerson23.setPhone("0023");
		testPerson23.setCity("CITY2");

		testPerson11 = injectPerson(testPerson11);
		testPerson12 = injectPerson(testPerson12);
		testPerson13 = injectPerson(testPerson13);
		testPerson21 = injectPerson(testPerson21);
		testPerson22 = injectPerson(testPerson22);
		testPerson23 = injectPerson(testPerson23);

		return new Person[] { testPerson11, testPerson12, testPerson13, testPerson21, testPerson22, testPerson23 };
	}

	MedicalRecord[] injectTestRecords(Person[] people) {

		// inject medical records with names and birthdates

		MedicalRecord testRecord11 = dataGenerator.generateTestRecord();
		testRecord11.setFirstName(people[0].getFirstName());
		testRecord11.setFirstName(people[0].getLastName());
		testRecord11.setBirthDate(new Date(1990, 4, 10)); // age 21

		MedicalRecord testRecord12 = dataGenerator.generateTestRecord();
		testRecord12.setFirstName(people[1].getFirstName());
		testRecord12.setFirstName(people[1].getLastName());
		testRecord12.setBirthDate(new Date(2010, 4, 10)); // age 11

		MedicalRecord testRecord13 = dataGenerator.generateTestRecord();
		testRecord13.setFirstName(people[2].getFirstName());
		testRecord13.setFirstName(people[2].getLastName());
		testRecord13.setBirthDate(new Date(1983, 4, 10)); // age 28

		MedicalRecord testRecord21 = dataGenerator.generateTestRecord();
		testRecord21.setFirstName(people[3].getFirstName());
		testRecord21.setFirstName(people[3].getLastName());
		testRecord21.setBirthDate(new Date(2005, 4, 10)); // age 16

		MedicalRecord testRecord22 = dataGenerator.generateTestRecord();
		testRecord22.setFirstName(people[4].getFirstName());
		testRecord22.setFirstName(people[4].getLastName());
		testRecord22.setBirthDate(new Date(2019, 4, 10)); // age 3

		MedicalRecord testRecord23 = dataGenerator.generateTestRecord();
		testRecord23.setFirstName(people[5].getFirstName());
		testRecord23.setFirstName(people[5].getLastName());
		testRecord23.setBirthDate(new Date(1960, 4, 10)); // age 61

		testRecord11 = injectRecord(testRecord11);
		testRecord12 = injectRecord(testRecord12);
		testRecord13 = injectRecord(testRecord13);
		testRecord21 = injectRecord(testRecord21);
		testRecord22 = injectRecord(testRecord22);
		testRecord23 = injectRecord(testRecord23);

		return new MedicalRecord[] { testRecord11, testRecord12, testRecord13, testRecord21, testRecord22,
				testRecord23 };
	}

	FireStation[] injectTestStations() {

		// inject fire stations with good and bad adresses

		FireStation testStation1 = dataGenerator.generateTestStation();
		testStation1.setAddress("TestAddress1");
		testStation1.setStationId(0);

		FireStation testStation2 = dataGenerator.generateTestStation();
		testStation2.setAddress("TestAddress2");
		testStation2.setStationId(1);

		FireStation testStation3 = dataGenerator.generateTestStation();
		testStation3.setAddress("TestAddress3");
		testStation3.setStationId(0);

		FireStation testStation4 = dataGenerator.generateTestStation();
		testStation4.setAddress("TestAddress4");
		testStation4.setStationId(1);

		testStation1 = injectFireStation(testStation1);
		testStation2 = injectFireStation(testStation2);
		testStation3 = injectFireStation(testStation3);
		testStation4 = injectFireStation(testStation4);

		return new FireStation[] { testStation1, testStation2, testStation3, testStation4 };
	}

	Person injectPerson(Person person) {

		if (person.isValid())
			return personRepo.save(person);
		else {

			System.out.println("Operation failed");
			return null;
		}
	}

	FireStation injectFireStation(FireStation station) {

		if (station.isValid())
			return stationRepo.save(station);
		else {

			System.out.println("Operation failed");
			return null;
		}
	}

	MedicalRecord injectRecord(MedicalRecord record) {

		if (record.isValid())
			return recordRepo.save(record);
		else {

			System.out.println("Operation failed");
			return null;
		}
	}
}