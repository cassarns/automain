-- This is the initialization file to create all the necessary tables for automain

CREATE TABLE IF NOT EXISTS Car (
  car_id    SERIAL PRIMARY KEY NOT NULL,
  make      VARCHAR(20) NOT NULL,
  model     VARCHAR(20) NOT NULL,
  year      INT NOT NULL,
  odometer_reading  FLOAT NOT NULL,
  car_type  VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS MaintenanceTask (
  car_id          BIGINT,
  maintenance_id  SERIAL PRIMARY KEY NOT NULL,
  idx             INT DEFAULT NULL,
  task_name       VARCHAR(200) NOT NULL,
  cost            FLOAT NOT NULL,
  task_time       FLOAT NOT NULL,
  FOREIGN KEY (car_id)
  REFERENCES  Car (car_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE  
);

CREATE TABLE IF NOT EXISTS DieselCar (
  diesel_id    SERIAL PRIMARY KEY NOT NULL,
  FOREIGN KEY (diesel_id)
  REFERENCES  Car (car_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS GasCar (
  gas_id    SERIAL NOT NULL,
  FOREIGN KEY (gas_id)
  REFERENCES  Car (car_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ElectricCar (
  electric_id    SERIAL PRIMARY KEY NOT NULL,
  FOREIGN KEY (electric_id)
  REFERENCES  Car (car_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);