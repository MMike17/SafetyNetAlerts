DROP TABLE IF EXISTS medicalRecords;

CREATE TABLE medicalRecords
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(250) NOT NULL,
	last_name VARCHAR(250) NOT NULL,
	birth_date DATE NOT NULL,
	medications ARRAY(VARCHAR(250)),
	allergies ARRAY(VARCHAR(250))
);