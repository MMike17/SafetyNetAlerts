package com.safetynet.alerts.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.util.StringUtils;

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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStationId() {
		return this.stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	/**
	 * Returns true if the object doesn't have null important fields
	 */
	@JsonIgnore
	public boolean isValid() {

		if (!StringUtils.hasText(address))
			return false;

		if (stationId <= -1)
			return false;

		return true;
	}
}