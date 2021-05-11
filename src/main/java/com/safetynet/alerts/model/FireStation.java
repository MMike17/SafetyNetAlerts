package com.safetynet.alerts.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fireStation")
/**
 * Class used to model the FireStation object in database
 * 
 * @author MikeMatthews
 */
public class FireStation {

	public FireStation() {
		
	}

	public FireStation(String address, Integer stationId) {

		this.address = address;
		this.stationId = stationId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "address")
	String address;

	@Column(name = "station")
	Integer stationId;

	/**
	 * Returns true if the object doesn't have null important fields
	 */
	@JsonIgnore
	public boolean isValid() {

		if (address == null || address == "")
			return false;

		if (stationId <= -1)
			return false;

		return true;
	}
}