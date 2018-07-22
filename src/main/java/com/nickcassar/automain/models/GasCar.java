/**
 * This is the class that represents a gas car
 */
package com.nickcassar.automain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.interfaces.ICMaintenance;

@Entity
@Table(name = "GasCar")
public class GasCar extends Car implements ICMaintenance {
  
  /*****************
   * Class Variables
   *****************/

  /**************
   * Constructors
   **************/

  // Empty constructor for Hibernate
  public GasCar() {
    super();
  }

  // Default constructor
  public GasCar(String make, String model, int year, double oReading, CarType type) {
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
    MaintenanceTask changeSparkPlugsTask = new MaintenanceTask("Change Spark Plugs", cost, time);
    getmTasks().add(changeSparkPlugsTask);
  }

	public void changeTransmissionFluid(double cost, double time) {
    MaintenanceTask changeTFluidTask = new MaintenanceTask("Change Transmission Fluid", cost, time);
    getmTasks().add(changeTFluidTask);
	}

	public void changeBattery(double cost, double time) {
    MaintenanceTask changeBatteryTask = new MaintenanceTask("Change Battery", cost, time);
    getmTasks().add(changeBatteryTask);
  }
}