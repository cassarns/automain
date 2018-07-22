package com.nickcassar.automain;

import java.util.List;

import javax.persistence.Query;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.models.Car;
import com.nickcassar.automain.models.DieselCar;
import com.nickcassar.automain.models.ElectricCar;
import com.nickcassar.automain.models.MaintenanceTask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CarTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public CarTest( String testName ) {
      super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
      return new TestSuite( CarTest.class );
  }

  /**
   * Test for creating different car objects and performing maintenance on them to see if the lists get updated
   */
  public void testApp() {

  DieselCar car = new DieselCar("Chevy", "Silverado", 2007, 0, CarType.TRUCK);
  ElectricCar eCar = new ElectricCar("Tesla", "Model S", 2018, 1500, CarType.SEDAN);

  car.changeBattery(50, 2.5);
  eCar.replaceBatterySystem(2000, 20);

  assertTrue(!car.getmTasks().isEmpty());
  assertTrue(!eCar.getmTasks().isEmpty());

  }
}
