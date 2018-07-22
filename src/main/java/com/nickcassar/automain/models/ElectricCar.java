/**
 * This is the class that represents an electric car
 */
package com.nickcassar.automain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.interfaces.EMaintenance;

@Entity
@Table(name = "ElectricCar")
public class ElectricCar extends Car implements EMaintenance {

  /*****************
   * Class Variables
   *****************/

  /**************
   * Constructors
   **************/

  // Empty constructor for Hibernate
  public ElectricCar() {
    super();
  }

  // Default constructor
  public ElectricCar(String make, String model, int year, double oReading, CarType type) {
    super(make, model, year, oReading, type);
  }


  /***************
   * Class Methods
   ***************/

	public void replaceBatterySystem(double time, double cost) {
    MaintenanceTask replaceBSystemTask = new MaintenanceTask("Battery System Replacement", cost, time);
    getmTasks().add(replaceBSystemTask);
	}


}
