package com.safetynet.alerts.model.responses;

import java.util.ArrayList;

import com.safetynet.alerts.model.Person;

/**
 * Class used to return list of children and adults at the provided adress
 * 
 * @see Person
 * 
 * @author Mike Matthews
 */
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<FullPerson> getChildren() {
		return this.children;
	}

	public void setChildren(ArrayList<FullPerson> children) {
		this.children = children;
	}

	public ArrayList<Person> getAdults() {
		return this.adults;
	}

	public void setAdults(ArrayList<Person> adults) {
		this.adults = adults;
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