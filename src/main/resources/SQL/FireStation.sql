DROP TABLE IF EXISTS fireStation;

CREATE TABLE fireStation
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	address VARCHAR(250) NOT NULL,
	station INT NOT NULL
);