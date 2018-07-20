/**
 * This is the class that represents a diesel car
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
@Table(name = "DieselCar")
public class DieselCar extends Car implements ICMaintenance {
  
  /*****************
   * Class Variables
   *****************/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long dieselId;

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

  public void changeOil(double cost, double time) {
    MaintenanceTask changeOilTask = new MaintenanceTask("Oil Change", cost, time);
    super.mTasks.add(changeOilTask);
	}

	public void changePlugs(double cost, double time) {
    MaintenanceTask changeGlowPlugsTask = new MaintenanceTask("Change Glow Plugs", cost, time);
    super.mTasks.add(changeGlowPlugsTask);
	}

	public void changeTransmissionFluid(double cost, double time) {
    MaintenanceTask changeTFluidTask = new MaintenanceTask("Change Transmission Fluid", cost, time);
    super.mTasks.add(changeTFluidTask);
	}

	public void changeBattery(double cost, double time) {
    MaintenanceTask changeBatteryTask = new MaintenanceTask("Change Battery", cost, time);
    super.mTasks.add(changeBatteryTask);
  }


}