package com.safetynet.alerts.model.responses;

import java.util.ArrayList;

/**
 * Class used to model the information necessary for a fire alert
 * 
 * @see FullPerson
 * 
 * @author Mike Matthews
 */
public class FireAlert {

	int correspondingStationID;
	ArrayList<FullPerson> inhabitants;

	public FireAlert(int correspondingStationID, ArrayList<FullPerson> inhabitants) {

		this.correspondingStationID = correspondingStationID;
		this.inhabitants = inhabitants;
	}

	public FireAlert() {

	}

	public int getCorrespondingStationID() {
		return this.correspondingStationID;
	}

	public void setCorrespondingStationID(int correspondingStationID) {
		this.correspondingStationID = correspondingStationID;
	}

	public ArrayList<FullPerson> getInhabitants() {
		return this.inhabitants;
	}

	public void setInhabitants(ArrayList<FullPerson> inhabitants) {
		this.inhabitants = inhabitants;
	}

	public boolean compare(FireAlert other) {

		if (correspondingStationID != other.getCorrespondingStationID())
			return false;

		if (inhabitants.size() != other.getInhabitants().size())
			return false;
		else {

			for (int i = 0; i < inhabitants.size(); i++) {

				if (!inhabitants.get(i).compare(other.getInhabitants().get(i)))
					return false;
			}
		}

		return true;
	}
}