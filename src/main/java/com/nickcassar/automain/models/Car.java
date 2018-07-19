/**
 * This is the main superclass for a Car that contains all information shared by all cars
 */
package com.nickcassar.automain.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nickcassar.automain.enums.CarType;

@Entity
public class Car implements Serializable {

  /*****************
   * Class Variables
   *****************/

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long carId;

  // The car's make
  private String make;

  // The car's model
  private String model;

  // The car's year
  private int year;

  // The odometer reading
  private double odometerReading;

  // The type of car
  private CarType type;

  /*******************
   * Constructors
   *******************/

  // Empty constructor for Hibernate
  public Car() {
  }

  public Car(String make, String model, int year, double oReading, CarType type) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.odometerReading = oReading;
    this.type = type;
  }
  /*********************
   * Getters and Setters
   *********************/

  public Long getCarId() {
    return carId;
  }

  public void setCarId(Long id) {
    this.carId = id;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public double getOdometerReading() {
    return this.odometerReading;
  }

  public void setOdometerReading(double oReading) {
    this.odometerReading = oReading;
  }

  public CarType getType() {
    return this.type;
  }

  public void setType(CarType type) {
    this.type = type;
  }
}
