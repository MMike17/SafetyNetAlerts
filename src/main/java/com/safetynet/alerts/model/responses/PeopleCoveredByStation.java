package com.safetynet.alerts.model.responses;

import java.io.Serializable;
import java.util.ArrayList;

import com.safetynet.alerts.model.Person;

import lombok.Data;

/**
 * Class used to return list of people and count of adults and children
 * 
 * @see Person
 * 
 * @author Mike Matthews
 */
@Data
public class PeopleCoveredByStation implements Serializable {

	ArrayList<Person> coveredPeople;
	int adultsCount;
	int childrenCount;

	public PeopleCoveredByStation(ArrayList<Person> coveredPeople, int childrenCount) {

		this.coveredPeople = coveredPeople;
		this.childrenCount = childrenCount;

		adultsCount = coveredPeople.size() - childrenCount;
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