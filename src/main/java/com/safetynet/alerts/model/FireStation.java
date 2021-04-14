package com.safetynet.alerts.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "fireStation")
/**
 * Class used to model the FireStation object in database
 * @author MikeMatthews
 */
public class FireStation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "address")
	String address;

	@Column(name = "station")
	Integer stationId;
}