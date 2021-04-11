package com.safetynet.alerts.model;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "medicalRecord")
public class MedicalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "birth_date")
	Date birthDate;

	@Column(name = "medications")
	String[] medications;

	@Column(name = "allergies")
	String[] allergies;
}