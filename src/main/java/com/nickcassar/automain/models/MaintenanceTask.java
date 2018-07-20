/**
 * MaintenanceTask holds information pertaining to a specific task performed on a car
 */

package com.nickcassar.automain.models;

public class MaintenanceTask {
  
  /*****************
   * Class Variables
   *****************/

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
  public MaintenanceTask( String taskName, double taskCost, double taskTime) {
    this.name = taskName;
    this.cost = taskCost;
    this.time = taskTime;
  }

  /*********************
   * Getters and Setters
   *********************/

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