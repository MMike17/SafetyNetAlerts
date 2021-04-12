DROP TABLE IF EXISTS person;

CREATE TABLE person
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(250) NOT NULL,
	last_name VARCHAR(250) NOT NULL,
	address VARCHAR(250) NOT NULL,
	city VARCHAR(250) NOT NULL,
	zip INT NOT NULL,
	phone VARCHAR(250) NOT NULL, 
	email VARCHAR(250) NOT NULL
);