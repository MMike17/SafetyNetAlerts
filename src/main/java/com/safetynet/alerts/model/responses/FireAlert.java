package com.safetynet.alerts.model.responses;

import java.util.ArrayList;

import lombok.Data;

/**
 * Class used to model the information necessary for a fire alert
 * 
 * @see FullPerson
 * 
 * @author Mike Matthews
 */
@Data
public class FireAlert {

	Integer correspondingStationID;
	ArrayList<FullPerson> inhabitants;

	public FireAlert(Integer correspondingStationID, ArrayList<FullPerson> inhabitants) {

		this.correspondingStationID = correspondingStationID;
		this.inhabitants = inhabitants;
	}
}