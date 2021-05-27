package com.safetynet.alerts.model.responses;

import java.util.ArrayList;

import com.safetynet.alerts.model.Person;

/**
 * Class used to return list of people and count of adults and children
 * 
 * @see Person
 * 
 * @author Mike Matthews
 */
public class PeopleCoveredByStation {

	ArrayList<Person> coveredPeople;
	int adultsCount;
	int childrenCount;

	public PeopleCoveredByStation(ArrayList<Person> coveredPeople, int childrenCount) {

		this.coveredPeople = coveredPeople;
		this.childrenCount = childrenCount;

		adultsCount = coveredPeople.size() - childrenCount;
	}

	public PeopleCoveredByStation() {
		coveredPeople = new ArrayList<Person>();
	}

	public ArrayList<Person> getCoveredPeople() {
		return this.coveredPeople;
	}

	public void setCoveredPeople(ArrayList<Person> coveredPeople) {
		this.coveredPeople = coveredPeople;
	}

	public int getAdultsCount() {
		return this.adultsCount;
	}

	public void setAdultsCount(int adultsCount) {
		this.adultsCount = adultsCount;
	}

	public int getChildrenCount() {
		return this.childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}

	public boolean compare(PeopleCoveredByStation other) {

		if (adultsCount != other.adultsCount)
			return false;

		if (childrenCount != other.childrenCount)
			return false;

		if (coveredPeople.size() != other.coveredPeople.size())
			return false;

		for (int i = 0; i < coveredPeople.size(); i++) {

			if (!coveredPeople.get(i).compare(other.coveredPeople.get(i)))
				return false;
		}

		return true;
	}
}