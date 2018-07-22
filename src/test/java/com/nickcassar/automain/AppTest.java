package com.nickcassar.automain;

import java.util.List;

import com.nickcassar.automain.controllers.DatabaseOperations;
import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.models.Car;
import com.nickcassar.automain.models.DieselCar;
import com.nickcassar.automain.models.ElectricCar;
import com.nickcassar.automain.models.GasCar;
import com.nickcassar.automain.models.MaintenanceTask;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public AppTest( String testName ) {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite( AppTest.class );
  }
  /**
   * Test for Hibernate and Database Persistence
   */
  public void testApp() {

    // Create some new Car objects and Maintenance Tasks
    ElectricCar eCar = new ElectricCar("Tesla", "Model S", 2015, 2000, CarType.SEDAN);
    DieselCar dCar = new DieselCar("Honda", "Accord", 2007, 0, CarType.CONVERTIBLE);
    GasCar gCar = new GasCar("For", "Explorer", 1998, 288940, CarType.SUV);

    // Diesel car tasks
    dCar.changeBattery(50, 2.5);
    dCar.changeBrakes(200, 4);

    // Gas car tasks
    gCar.changeBattery(75, 1);
    gCar.repairBody(2500, 15);

    // Electric car tasks
    eCar.replaceBatterySystem(5000, 50);

    // Try and add the cars to the database
    assertTrue(addCar(dCar));
    //assertTrue(addCar(gCar));
    //assertTrue(addCar(eCar));

    //assertTrue(listCars());
    //assertTrue(listMaintenance());

	}

  // Method to attempt to add the car to the database
  // Returns true if successful
  public boolean addCar(Car c) {
    boolean success = true;
    try {
      DatabaseOperations.createRecord(c);
    } catch (Exception e) {
      e.printStackTrace(); 
      success = false;
   }
    return success;
  }



  // Method to list all the cars in the database
  public boolean listCars() {
    boolean success = true;
    try {
      List<Car> list = DatabaseOperations.displayCarRecords();
      for (Car c: list) {
        System.out.println(c.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
      success = false;
    }
    return success;
  }

  // Method to list all the maintenance tasks in the database
  public boolean listMaintenance() {
    boolean success = true;
    try {
      List<MaintenanceTask> list = DatabaseOperations.displayMaintenaceRecords();
      for (MaintenanceTask mt: list) {
        System.out.println(mt.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
      success = false;
    }
    return success;
  }
}
