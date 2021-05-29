package com.safetynet.alerts.model.responses;

import java.util.ArrayList;

/**
 * Class used to model a complete household with all informations
 * 
 * @see FullPerson
 * 
 * @author Mike Matthews
 */
public class HouseHold {

	public HouseHold(String address, ArrayList<FullPerson> people) {

		this.address = address;
		this.people = people;
	}

	public HouseHold() {

	}

	String address;
	ArrayList<FullPerson> people;

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<FullPerson> getPeople() {
		return this.people;
	}

	public void setPeople(ArrayList<FullPerson> people) {
		this.people = people;
	}

	public boolean compare(HouseHold other) {

		if (!address.equals(other.getAddress()))
			return false;

		if (people.size() != other.getPeople().size())
			return false;

		for (int i = 0; i < people.size(); i++) {

			if (!other.getPeople().get(i).compare(people.get(i)))
				return false;
		}

		return true;
	}
}