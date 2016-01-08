/*Created By: Daniel Myers*/

DROP DATABASE IF EXISTS MavTunes;

CREATE DATABASE MavTunes;

USE MavTunes;

CREATE TABLE Apps(
	Title VARCHAR(30) NOT NULL PRIMARY KEY,
	Type VARCHAR(30) NOT NULL,
	Developer VARCHAR(30) NOT NULL,
	Price FLOAT(7.2)
);

CREATE TABLE Music(
	Title VARCHAR(30) NOT NULL PRIMARY KEY,
	Type VARCHAR(30) NOT NULL,
	Artist VARCHAR(30) NOT NULL,
	NumberOfSongs INT(5) NOT NULL,
	Price FLOAT(7.2) NOT NULL
);

CREATE TABLE Customers(
	CustID INT(2) NOT NULL PRIMARY KEY,
	Name VARCHAR(30) NOT NULL,
	Street VARCHAR(30) NOT NULL,
	City VARCHAR(30) NOT NULL,
	State VARCHAR(30) NOT NULL,
	Zip INT(5)
);

CREATE TABLE Products(
	CustID INT(2) NOT NULL,
	Type VARCHAR(30) NOT NULL,
	Title VARCHAR(30) NOT NULL,
	FOREIGN KEY(CustID) REFERENCES Customers(CustID)
);

INSERT INTO Apps(Title, Type, Developer, Price)
VALUES
	('Tennis', 'Game', 'Ubisoft', 9.99),
	('Learn Java', 'Education', 'Adobe', 5.99),
	('Pages', 'Productivity', 'Apple', 9.99),
	('Midnight', 'Game', 'Hyperbolic Magnetism', .99),
	('Asphalt HD', 'Game', 'GameLoft', 3.99),
	('MBA 101', 'Education', 'Tech University', 4.99
);

INSERT INTO Music(Title, Type, Artist, NumberOfSongs, Price)
VALUES
	('Meet Bach', 'Classical', 'YoYo Ma', 5, .99),
	('Rihanna', 'Rock', 'Rihanna', 8, 1.29),
	('For the Road', 'Rock', 'Swift', 12, .99),
	('Online', 'Country', 'Brad Paisley', 3, .79),
	('Best of Boston', 'Rock', 'Boston', 10, .99),
	('Wings', 'Rock', 'McCartney', 6, .50),
	('Star Trek', 'Classical', 'Giachinno', 15, 1.29
);


	

