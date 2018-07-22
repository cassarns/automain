/**
 * This is the class that represents a diesel car
 */
package com.nickcassar.automain.models;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.interfaces.ICMaintenance;

public class DieselCar extends Car implements ICMaintenance {
  
  /*****************
   * Class Variables
   *****************/

  /**************
   * Constructors
   **************/

  // Empty constructor for Hibernate
  public DieselCar() {
    super();
  }

  // Default constructor
  public DieselCar(String make, String model, int year, double oReading, CarType type) {
    super(make, model, year, oReading, type);
  }

  /***************
   * Class Methods
   ***************/

  public void changeOil(double cost, double time) {
    MaintenanceTask changeOilTask = new MaintenanceTask("Oil Change", cost, time);
    getmTasks().add(changeOilTask);
	}

	public void changePlugs(double cost, double time) {
    MaintenanceTask changeGlowPlugsTask = new MaintenanceTask("Change Glow Plugs", cost, time);
    getmTasks().add(changeGlowPlugsTask);
	}

	public void changeTransmissionFluid(double cost, double time) {
    MaintenanceTask changeTFluidTask = new MaintenanceTask("Change Transmission Fluid", cost, time);
    getmTasks().add(changeTFluidTask);
	}

	public void changeBattery(double cost, double time) {
    MaintenanceTask changeBatteryTask = new MaintenanceTask("Change Battery", cost, time);
    getmTasks().add(changeBatteryTask);
  }

  /*********************
   * Getters and Setters
   *********************/

}