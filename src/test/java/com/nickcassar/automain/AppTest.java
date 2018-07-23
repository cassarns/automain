package com.nickcassar.automain;

import java.util.ArrayList;
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

    // Try and add the cars to the database and at each stage ensure the tables are the right size
    assertTrue(addCar(dCar));
    assertTrue(listCars().size() == 1);
    assertTrue(addCar(gCar));
    assertTrue(listCars().size() == 2);
    assertTrue(addCar(eCar));
    assertTrue(listCars().size() == 3);

    // List the maintenance tasks in the database and ensure the table is the right size
    assertTrue(listMaintenance().size() == 5);

    // Delete a maintenance task from a car in the database
    deleteMT(dCar.getmTasks().get(0));

    assertTrue(listMaintenance().size() == 4);

    updateCar(dCar);

    Car changedCar = findCar(dCar.getCarId());
    assertTrue(changedCar.getMake().contentEquals("CHANGED"));

    deleteCar(dCar);
    assertTrue(listCars().size() == 2);

    // Delete all car records in the database and ensure the table has no tuples
    assertTrue(deleteAll().size() == 0);

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
  public List<Car> listCars() {
    List<Car> list = new ArrayList<Car>();
    try {
      list = DatabaseOperations.displayCarRecords();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // Method to find a car record in the database based on id
  public Car findCar(Long carId) {
    return DatabaseOperations.findCarRecord(carId);
  }

  // Method to find a car record in the database based on id
  public MaintenanceTask findMT(Long mtId) {
    return DatabaseOperations.findMTRecord(mtId);
  }

  // Method to list all the maintenance tasks in the database
  public List<MaintenanceTask> listMaintenance() {
    List<MaintenanceTask> list = new ArrayList<MaintenanceTask>();
    try {
      list = DatabaseOperations.displayMaintenaceRecords();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  // Method to update a car record in the database
  public void updateCar(Car c) {
    DatabaseOperations.updateCarRecord(c.getCarId(), "CHANGED", "CHANGED", 9999, 0, CarType.CONVERTIBLE);
  }

  // Method to delete a car record from the database
  public void deleteCar(Car c) {
    DatabaseOperations.deleteCarRecord(c.getCarId());
  }

  // Method to delete a maintenance task
  public void deleteMT(MaintenanceTask mt) {
    DatabaseOperations.deleteMTRecord(mt.getMaintenanceId());
  }

  // Method to delete all records
  public List<Car> deleteAll() {
    List<Car> list = new ArrayList<Car>();
    try {
      DatabaseOperations.deleteAllCarRecords();
      list = DatabaseOperations.displayCarRecords();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
