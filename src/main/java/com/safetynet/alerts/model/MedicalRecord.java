package com.safetynet.alerts.model;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.util.StringUtils;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;

@Data
@Entity
@Table(name = "medicalRecord")
/**
 * Class used to model the MedicalRecord object in database
 * 
 * @author MikeMatthews
 */
@SuppressFBWarnings(value = { "EI_EXPOSE_REP",
		"EI_EXPOSE_REP2" }, justification = "Lombok @Data anotation will always violate best practices")
public class MedicalRecord {

	public MedicalRecord() {

	}

	public MedicalRecord(String firstName, String lastName) {

		this.firstName = firstName;
		this.lastName = lastName;
	}

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

	/**
	 * Returns true if the object doesn't have null important fields
	 */
	@JsonIgnore
	public boolean isValid() {

		if (!StringUtils.hasText(firstName))
			return false;

		if (!StringUtils.hasText(lastName))
			return false;

		if (birthDate == null)
			return false;

		return true;
	}

	/**
	 * Alternative of the "equals" method to say if objects contain the same
	 * informations
	 */
	public boolean compare(MedicalRecord record) {

		if (!firstName.equals(record.getFirstName()))
			return false;

		if (!lastName.equals(record.getLastName()))
			return false;

		if (!birthDate.toLocalDate().equals(record.getBirthDate().toLocalDate()))
			return false;

		if (allergies.length != record.getAllergies().length)
			return false;
		else {

			for (int i = 0; i < allergies.length; i++) {

				if (!allergies[i].equals(record.getAllergies()[i]))
					return false;
			}
		}

		if (medications.length != record.getMedications().length)
			return false;
		else {

			for (int i = 0; i < medications.length; i++) {

				if (!medications[i].equals(record.getMedications()[i]))
					return false;
			}
		}

		return true;
	}
}