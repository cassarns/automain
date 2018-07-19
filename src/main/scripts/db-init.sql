-- This is the initialization file to create all the necessary tables for automain

CREATE TABLE Cars (
  carId SERIAL PRIMARY KEY NOT NULL,
  carType VARCHAR(20) NOT NULL
);

CREATE TABLE Maintenance (
  maintenanceId SERIAL PRIMARY KEY NOT NULL
);
