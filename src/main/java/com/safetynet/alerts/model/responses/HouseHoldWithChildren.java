package com.safetynet.alerts.model.responses;

import java.util.ArrayList;

import com.safetynet.alerts.model.Person;

import lombok.Data;

/**
 * Class used to return list of children and adults at the provided adress
 * 
 * @see Person
 * 
 * @author Mike Matthews
 */
@Data
public class HouseHoldWithChildren {

	String address;
	ArrayList<FullPerson> children;
	ArrayList<Person> adults;

	public HouseHoldWithChildren(String address, ArrayList<FullPerson> children, ArrayList<Person> adults) {

		this.address = address;
		this.children = children;
		this.adults = adults;
	}

	public HouseHoldWithChildren() {

		children = new ArrayList<FullPerson>();
		adults = new ArrayList<Person>();
	}

	public boolean compare(HouseHoldWithChildren other) {

		if (!address.equals(other.getAddress()))
			return false;

		if (children.size() != other.getChildren().size())
			return false;
		else {

			for (int i = 0; i < children.size(); i++) {

				if (!children.get(i).compare(other.getChildren().get(i)))
					return false;
			}
		}

		if (adults.size() != other.getAdults().size())
			return false;
		else {

			for (int i = 0; i < adults.size(); i++) {

				if (!adults.get(i).compare(other.getAdults().get(i)))
					return false;
			}
		}

		return true;
	}
}