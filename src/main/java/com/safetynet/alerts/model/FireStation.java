package com.safetynet.alerts.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "fireStation")
public class FireStation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "address")
	String address;

	@Column(name = "station")
	Integer stationId;
}