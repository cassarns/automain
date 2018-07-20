-- This is the initialization file to create all the necessary tables for automain

CREATE TABLE Car (
  car_id    SERIAL PRIMARY KEY NOT NULL,
  make      VARCHAR(20) NOT NULL,
  model     VARCHAR(20) NOT NULL,
  year      INT NOT NULL,
  odometer_reading  FLOAT NOT NULL,
  car_type  VARCHAR(20) NOT NULL
);

CREATE TABLE Maintenance (
  maintenanceId SERIAL PRIMARY KEY NOT NULL
);
