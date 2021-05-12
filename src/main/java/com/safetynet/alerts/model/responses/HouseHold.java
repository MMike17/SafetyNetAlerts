package com.safetynet.alerts.model.responses;

import java.util.ArrayList;

import lombok.Data;

/**
 * Class used to model a complete household with all informations
 * 
 * @see FullPerson
 * 
 * @author Mike Matthews
 */
@Data
public class HouseHold {

	String address;
	ArrayList<FullPerson> people;

	public HouseHold(String address, ArrayList<FullPerson> people) {

		this.address = address;
		this.people = people;
	}
}