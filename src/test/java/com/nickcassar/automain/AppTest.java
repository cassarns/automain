package com.nickcassar.automain;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.Transaction;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.models.Car;
import com.nickcassar.automain.models.DieselCar;
import com.nickcassar.automain.models.ElectricCar;
import com.nickcassar.automain.models.GasCar;
import com.nickcassar.automain.models.MaintenanceTask;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
  // The SessionFactory
  private static SessionFactory factory;
  /**
   * Test for Hibernate and Database Persistence
   */
  public void testApp() {

    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) { 
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex); 
    }

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
    assertTrue(addCar(gCar));
    assertTrue(addCar(eCar));

    assertTrue(listCars());

	}

  // Method to attempt to add the car to the database
  // Returns true if successful
  public boolean addCar(Car c) {
    Session session = factory.openSession();
    Transaction tx = null;
    boolean success = true;
    try {
      tx = session.getTransaction();
      tx.begin();
      session.save(c);
      tx.commit();
    } catch (HibernateException e) {
      if (tx!=null) {
        tx.rollback();
      }
      e.printStackTrace(); 
      success = false;
   } finally {
      session.close();
   }
    return success;
  }

  // Method to list all the cars in the database
  public boolean listCars() {
    Session session = factory.openSession();
    Transaction tx = null;
    boolean success = true;
    try {
      tx = session.getTransaction();
      tx.begin();
      Query query = session.createQuery("from Car");
      
      List<Car> list = query.getResultList();
      

      for (Car c: list) {
        System.out.println("Car ID: " + c.getCarId());
        System.out.println("Car Make: " + c.getMake());
        if (!c.getmTasks().isEmpty()) {
          for (MaintenanceTask task : c.getmTasks()) {
            System.out.println(task.getName() + ": " + task.getCost());
          }
        }
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
      success = false;
    } finally {
      session.close();
    }
    return success;
  }
}
