package com.safetynet.alerts.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.safetynet.alerts.exceptions.InvalidObjectException;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.responses.FireAlert;
import com.safetynet.alerts.model.responses.FullPerson;
import com.safetynet.alerts.model.responses.HouseHold;
import com.safetynet.alerts.model.responses.HouseHoldWithChildren;
import com.safetynet.alerts.model.responses.PeopleCoveredByStation;
import com.safetynet.alerts.service.FireStationService;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to receive special requests which need multiple services
 * 
 * @see PersonController
 * @see FireStationController
 * @see MedicalRecordController
 * 
 * @see PeopleCoveredByStation
 * @see HouseHoldWithChildren
 * @see FullPerson
 * @see FireAlert
 * 
 * @author Mike Matthews
 */
@RestController
public class CompositeController {

	@Autowired
	FireStationService fireStationService;

	@Autowired
	PersonService personService;

	@Autowired
	MedicalRecordService medicalRecordService;

	/**
	 * Returns a list of all people covered by a station and the number of children
	 * and adults
	 * 
	 * @see PeopleCoveredByStation
	 * 
	 * @param stationID the stationID of the station
	 */
	@GetMapping("/firestation")
	public PeopleCoveredByStation getPersonsCoveredByStation(@RequestParam("stationNumber") final Integer stationID) {

		if (stationID == null || stationID <= 0)
			throw new InvalidObjectException();

		ArrayList<String> adresses = fireStationService.getAddressesFromStationID(stationID);

		if (adresses == null) {

			System.out.println("No adresses found for this station ID");
			return null;
		}

		ArrayList<Person> selectedPeople = new ArrayList<Person>();

		for (String adress : adresses)
			selectedPeople.addAll(personService.getPeopleAtAddress(adress));

		if (selectedPeople.size() <= 0) {

			System.out.println("Nobody lives at the provided adresses");
			return null;
		}

		int childrenCount = 0;

		for (Person person : selectedPeople) {

			try {

				if (getPersonAge(person) <= 18)
					childrenCount++;

			} catch (Exception exception) {

				System.out.println(exception);
				throw new InvalidObjectException();
			}
		}

		return new PeopleCoveredByStation(selectedPeople, childrenCount);
	}

	/**
	 * Returns a list of all children at provided adress and the other inhabitants
	 * of the household
	 * 
	 * @see HouseHoldWithChildren
	 */
	@GetMapping("/childAlert")
	public HouseHoldWithChildren getChildrenAtAdress(@RequestParam("address") final String address) {

		if (address == null || address == "")
			throw new InvalidObjectException();

		ArrayList<Person> inhabitants = personService.getPeopleAtAddress(address);

		ArrayList<FullPerson> children = new ArrayList<FullPerson>();
		ArrayList<Person> adults = new ArrayList<Person>();

		for (Person inhabitant : inhabitants) {

			try {

				int personAge = getPersonAge(inhabitant);

				if (personAge <= 18)
					children.add(new FullPerson(inhabitant, null, personAge));
				else
					adults.add(inhabitant);

			} catch (Exception exception) {

				System.out.println(exception);
				throw new InvalidObjectException();
			}
		}

		return new HouseHoldWithChildren(address, children, adults);
	}

	/**
	 * Returns a list of all phone numbers of people covered by a station
	 * 
	 * @see Person#getPhone()
	 */
	@GetMapping("/phoneAlert")
	public ArrayList<String> getPhoneNumbersForCoveredPeople(@RequestParam("firestation") final Integer stationID) {

		if (stationID == null || stationID <= 0)
			throw new InvalidObjectException();

		ArrayList<String> addresses = fireStationService.getAddressesFromStationID(stationID);
		ArrayList<String> phoneNumbers = new ArrayList<String>();

		if (addresses == null) {

			System.out.println("No adresses found for this station ID");
			return null;
		}

		for (String address : addresses) {

			ArrayList<Person> people = personService.getPeopleAtAddress(address);

			if (people.size() != 0) {

				for (Person person : people)
					phoneNumbers.add(person.getPhone());

			} else
				System.out.println("Nobody lives at the address : " + address);
		}

		if (phoneNumbers.size() <= 0) {

			System.out.println("Didn't find any phone numbers for the provided stationID : " + stationID);
			return null;
		}

		return phoneNumbers;
	}

	/**
	 * Returns the list of inhabitants with full information and the stationID of
	 * the station in charge of the provided adress
	 * 
	 * @see Person
	 * @see MedicalRecord
	 */
	@GetMapping("/fire")
	public FireAlert getPeopleAndStationIDAtAddress(@RequestParam("address") final String address) {

		if (address == null || address == "")
			throw new InvalidObjectException();

		ArrayList<Person> people = personService.getPeopleAtAddress(address);

		if (people.size() <= 0) {

			System.out.println("Nobody lives at the provided adresses");
			return null;
		}

		ArrayList<MedicalRecord> records = new ArrayList<MedicalRecord>();
		ArrayList<String> missingRecords = new ArrayList<String>();

		for (Person person : people) {

			MedicalRecord record = medicalRecordService.getRecordForPerson(person);

			if (record == null)
				missingRecords.add(person.getFirstName() + " " + person.getLastName());

			records.add(record);
		}

		if (missingRecords.size() > 0) {

			System.out.println(
					"The following people don't have any medical records : " + String.join(", ", missingRecords));
		}

		ArrayList<FullPerson> fullPeople = new ArrayList<FullPerson>();

		for (int i = 0; i < people.size(); i++) {

			int age = 0;

			try {
				age = getPersonAge(people.get(i));
			} catch (Exception exception) {

				System.out.println(exception);
				throw new InvalidObjectException();
			}

			fullPeople.add(new FullPerson(people.get(i), records.get(i), age));
		}

		return new FireAlert(fireStationService.getStationIDFromAddress(address), fullPeople);
	}

	/**
	 * Returns a list of households with full informations of inhabitants
	 * 
	 * @see HouseHold
	 * @see FullPerson
	 * @see Person
	 * @see MedicalRecord
	 */
	@GetMapping("/flood/stations")
	public ArrayList<HouseHold> getHouseHoldsCoveredByStations(@RequestParam("stations") final Integer[] stationIDs) {

		if (stationIDs == null || stationIDs.length <= 0 || stationIDs[0] <= 0)
			throw new InvalidObjectException();

		ArrayList<HouseHold> selectedHouseHolds = new ArrayList<HouseHold>();

		// get list of unique addresses
		ArrayList<String> selectedAddresses = new ArrayList<String>();

		for (Integer ID : stationIDs) {

			ArrayList<String> currentAddresses = fireStationService.getAddressesFromStationID(ID);

			for (String address : currentAddresses) {

				if (!selectedAddresses.contains(address))
					selectedAddresses.add(address);
			}
		}

		for (String currentAddress : selectedAddresses) {

			ArrayList<FullPerson> fullInhabitants = new ArrayList<FullPerson>();
			ArrayList<Person> inhabitants = personService.getPeopleAtAddress(currentAddress);

			for (Person person : inhabitants) {

				MedicalRecord record = medicalRecordService.getRecordForPerson(person);
				Integer personAge = -1;

				try {
					personAge = getPersonAge(person);
				} catch (Exception exception) {

					System.out.println(exception);
					throw new InvalidObjectException();
				}

				fullInhabitants.add(new FullPerson(person, record, personAge));
			}

			selectedHouseHolds.add(new HouseHold(currentAddress, fullInhabitants));
		}

		return selectedHouseHolds;
	}

	/**
	 * Returns a list of people with full infos from provided first and last names
	 * 
	 * @see FullPerson
	 * @see Person
	 * @see MedicalRecord
	 */
	@GetMapping("/personInfo")
	public ArrayList<FullPerson> getFullInfoFromName(@RequestParam("firstName") final String firstName,
			@RequestParam("lastName") final String lastName) {

		if (firstName == null || firstName == "" || lastName == null || lastName == "")
			throw new InvalidObjectException();

		ArrayList<FullPerson> selectedFullPeople = new ArrayList<FullPerson>();
		ArrayList<Person> selectedPeople = personService.getPeopleFromName(firstName, lastName);

		for (Person person : selectedPeople) {

			MedicalRecord record = medicalRecordService.getRecordForPerson(person);
			Integer personAge = -1;

			try {
				personAge = getPersonAge(person);
			} catch (Exception exception) {

				System.out.println(exception);
				throw new InvalidObjectException();
			}

			selectedFullPeople.add(new FullPerson(person, record, personAge));
		}

		return selectedFullPeople;
	}

	/**
	 * Returns a list of emails of all people living at a certain address
	 * 
	 * @see Person
	 */
	@GetMapping("communityEmail")
	public ArrayList<String> getEmailsOfPeopleInCity(@RequestParam("city") final String cityName) {

		if (cityName == null || cityName == "")
			throw new InvalidObjectException();

		ArrayList<String> emails = new ArrayList<String>();
		ArrayList<Person> people = personService.getPeopleFromCity(cityName);

		for (Person person : people)
			emails.add(person.getEmail());

		return emails;
	}

	/**
	 * Returns age of the provided Person
	 */
	int getPersonAge(Person person) throws Exception {

		MedicalRecord record = medicalRecordService.getRecordForPerson(person);

		if (record == null)
			return -1;

		LocalDate currentDate = new Date(System.currentTimeMillis()).toLocalDate();
		LocalDate birthDate = record.getBirthDate().toLocalDate();

		int personAge = currentDate.getYear() - birthDate.getYear();

		return personAge;
	}
}