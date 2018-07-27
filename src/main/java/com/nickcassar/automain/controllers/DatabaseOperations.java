/**
 * DatabaseOperations is responsible for interacting with the database and
 * providing CRUD functionality to the database
 */

package com.nickcassar.automain.controllers;

import java.util.ArrayList;
import java.util.List;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.models.Car;
import com.nickcassar.automain.models.MaintenanceTask;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class DatabaseOperations {

  private static Session session;
  private static SessionFactory sessionFactory;

  public final static Logger logger = Logger.getLogger(DatabaseOperations.class);

  /**
   * Method to build a session factory object
   */
   private static SessionFactory buildASessionFactory() {
    // Pass the configuration properties from the hibernate.cfg.xml file
    try {
      sessionFactory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) { 
      System.err.println("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex); 
    }
    return sessionFactory;
  }

  /**
   * Method to create a new Car record in the database
   */
  public static void createRecord(String make, String model, int year, double oReading, String carType) {
    try {
      // Get the session
      session = buildASessionFactory().openSession();
      // Get the transaction
      session.beginTransaction();
      CarType type = null;
      if (carType.contentEquals("HATCHBACK")) {
        type = CarType.HATCHBACK;
      } else if (carType.contentEquals("SEDAN")) {
        type = CarType.SEDAN;
      } else if (carType.contentEquals("MPV")) {
        type = CarType.MPV;
      } else if (carType.contentEquals("SUV")) {
        type = CarType.SUV;
      } else if (carType.contentEquals("CROSSOVER")) {
        type = CarType.CROSSOVER;
      } else if (carType.contentEquals("COUPE")) {
        type = CarType.COUPE;
      } else if (carType.contentEquals("CONVERTIBLE")) {
        type = CarType.CONVERTIBLE;
      } else if (carType.contentEquals("TRUCK")) {
        type = CarType.TRUCK;
      }
      Car c = new Car(make, model, year, oReading, type);
      // Save the car object
      session.save(c);
      // Commit the transaction
      session.getTransaction().commit();
      // Log the event
      logger.info("\nSuccessfully created Car Record in the Database!\n");
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        // If an error occurs, rollback the transaction
        logger.info("\n Error occurred. Transaction is being rolled back.\n");
        session.getTransaction().rollback();
        e.printStackTrace();
      }
    } finally {
      if (session != null) {
        // Close the session
        session.close();
      }
    }
  }

  /**
   * Method to create a Car record with existing data in the database
   * Also adds any maintenance that might be associated with it
   */
  public static void createRecord(Car c) {
    try {
      // Get the session
      session = buildASessionFactory().openSession();
      // Get the transaction
      session.beginTransaction();
      // Save the car object
      session.save(c);
      // Commit the transaction
      session.getTransaction().commit();
      // Log the event
      logger.info("\nSuccessfully created Car Record in the Database!\n");
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        // If an error occurs, rollback the transaction
        logger.info("\n Error occurred. Transaction is being rolled back.\n");
        session.getTransaction().rollback();
        e.printStackTrace();
      }
    } finally {
      if (session != null) {
        // Close the session
        session.close();
      }
    }
  }

  /**
   * Method to display the list of cars in the database
   */
  @SuppressWarnings("unchecked")
  public static List<Car> displayCarRecords() {
    List<Car> cars = new ArrayList<Car>();        
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      cars = session.createQuery("FROM Car").list();
    } catch(Exception e) {
      if(null != session.getTransaction()) {
          logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
          session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if(session != null) {
          session.close();
      }
    }
    return cars;
  }
    /**
   * Method to display the list of maintenance tasks in the database
   */
  @SuppressWarnings("unchecked")
  public static List<MaintenanceTask> displayMaintenaceRecords() {
    List<MaintenanceTask> mTasks = new ArrayList<MaintenanceTask>();        
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      mTasks = session.createQuery("FROM MaintenanceTask").list();
    } catch(Exception e) {
      if(session.getTransaction() != null) {
          logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
          session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if(session != null) {
          session.close();
      }
    }
    return mTasks;
  }

  /**
   * Method to update a car record with new information
   */
  public static void updateCarRecord(Long carId, String make, String model, int year, double oReading, CarType type) {
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      Car c = (Car) session.get(Car.class, carId);
      c.setMake(make);
      c.setModel(model);
      c.setOdometerReading(oReading);
      c.setType(type);
      c.setYear(year);
      session.getTransaction().commit();
      logger.info("\nCar id: " + carId + " record updated in the database");
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * Method to update a maintenance task record with new information
   */
  public static void updateMTRecord(Long mtId, String taskName, double taskCost, double taskTime) {
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      MaintenanceTask mt = (MaintenanceTask) session.get(MaintenanceTask.class, mtId);
      mt.setName(taskName);
      mt.setCost(taskCost);
      mt.setTime(taskTime);
      session.getTransaction().commit();
      logger.info("\nMaintenanceTask id: " + mtId + " record updated in the database");
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * Method to delete a car record from the database
   */
  public static void deleteCarRecord(Long carId) {
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      Car c = session.get(Car.class, carId);
      session.delete(c);
      session.getTransaction().commit();
      logger.info("\nCar record: " + carId + " deleted!");
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

    /**
   * Method to find a car record from the database
   */
  public static Car findCarRecord(Long carId) {
    Car c = null;
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      c = session.get(Car.class, carId);
      logger.info("\nCar record: " + carId + " found!");
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return c;
  }

  /**
   * Method to find a maintenance task record from the database
   */
  public static MaintenanceTask findMTRecord(Long mtId) {
    MaintenanceTask mt = null;
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      mt = session.get(MaintenanceTask.class, mtId);
      logger.info("\nMaintenanceTask record: " + mtId + " found!");
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return mt;
  }

  /**
   * Method to delete a maintenance task record from the database
   */
  public static void deleteMTRecord(Long mtId) {
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      MaintenanceTask mt = session.get(MaintenanceTask.class, mtId);
      session.delete(mt);
      session.getTransaction().commit();
      logger.info("\nMaintenance Task record: " + mtId + " deleted!");
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * Method to delete all maintenance records from the database
   */
  public static void deleteAllMTRecords() {
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      // Build a query to delete all maintenance tasks from the database
      Query query = session.createQuery("DELETE FROM MaintenanceTask");
      query.executeUpdate();
      // Commit the transaction
      session.getTransaction().commit();
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  /**
   * Method to delete all Car records from the database
   * This will cascade and remove all tuples from the other tables
   */
  public static void deleteAllCarRecords() {
    try {
      // Getting Session Object From SessionFactory
      session = buildASessionFactory().openSession();
      // Getting Transaction Object From Session Object
      session.beginTransaction();
      // Build a query to delete all maintenance tasks from the database
      Query query = session.createQuery("DELETE FROM Car");
      query.executeUpdate();
      // Commit the transaction
      session.getTransaction().commit();
    } catch (Exception e) {
      if(session.getTransaction() != null) {
        logger.info("\nError occurred. Transaction Is Being Rolled Back.\n");
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
}
