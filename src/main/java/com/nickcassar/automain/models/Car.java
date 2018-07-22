/**
 * This is the main superclass for a Car that contains all information shared by all cars
 */
package com.nickcassar.automain.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.interfaces.BasicMaintenance;

@Entity
@Table(name = "Car")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Car implements Serializable, BasicMaintenance {

  /*****************
   * Class Variables
   *****************/
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

  // The list of maintenance tasks performed on the vehicle
  @OneToMany(mappedBy = "idx", cascade = CascadeType.ALL)
  private List<MaintenanceTask> mTasks;

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

    //Initialize the mTasks to an empty ArrayList
    this.mTasks = new ArrayList<MaintenanceTask>();
  }

  /***************
   * Class Methods
   ***************/

  public void rotateTires(double cost, double time) {
    MaintenanceTask rotateTireTask = new MaintenanceTask("Tire Rotation", cost, time);
    mTasks.add(rotateTireTask);
  }

  public void changeTires(double cost, double time) {
    MaintenanceTask changeTireTask = new MaintenanceTask("Tire Change", cost, time);
    mTasks.add(changeTireTask);
  }

  public void changeBrakes(double cost, double time) {
    MaintenanceTask changeBrakeTask = new MaintenanceTask("Brake Change", cost, time);
    mTasks.add(changeBrakeTask);
  }
  
  public void repairBody(double cost, double time) {
    MaintenanceTask repairBodyTask = new MaintenanceTask("Autobody Repair", cost, time);
    mTasks.add(repairBodyTask);
  }

  @Override
  public String toString() {
    return  "Make: " + this.make + "\n" +
            "Model: " + this.model + "\n" +
            "Year: " + this.year + "\n" +
            "Odometer: " + this.odometerReading + "\n" +
            "Type: " + this.type + "\n";
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

  public List<MaintenanceTask> getmTasks() {
    return this.mTasks;
  }

  public void setmTasks(List<MaintenanceTask> mTasks) {
    this.mTasks = mTasks;
  }
}
