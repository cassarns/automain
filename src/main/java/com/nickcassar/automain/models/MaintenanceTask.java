/**
 * MaintenanceTask holds information pertaining to a specific task performed on a car
 */

package com.nickcassar.automain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "MaintenanceTask")
public class MaintenanceTask {
  
  /*****************
   * Class Variables
   *****************/

  // The maintenance id
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long maintenanceId;

  // The name of the task
  private String name;

  // The cost of the task in dollars
  private double cost;

  // The length of time the task takes in hours
  private double time;

  /**************
   * Constructors
   **************/
  
  // Empty constructor for Hibernate
  public MaintenanceTask() {
  }

  // Default constructor
  public MaintenanceTask(String taskName, double taskCost, double taskTime) {
    this.name = taskName;
    this.cost = taskCost;
    this.time = taskTime;
  }

  /***************
   * Class Methods
   ***************/

  @Override
  public String toString() {
    return  "Task: " + this.name + "\n" +
            "Cost: " + this.cost + "\n" +
            "Time Needed: " + this.time + "\n";
  }

  /*********************
   * Getters and Setters
   *********************/

  public Long getMaintenanceId() {
    return maintenanceId;
  }

  public void setMaintenanceId(Long id) {
    this.maintenanceId = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getCost() {
    return this.cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public double getTime() {
    return this.time;
  }

  public void setTime(double time) {
    this.time = time;
  }
}