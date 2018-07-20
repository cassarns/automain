package com.nickcassar.automain;

import java.util.List;

import javax.persistence.Query;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.models.Car;
import com.nickcassar.automain.models.DieselCar;
import com.nickcassar.automain.models.ElectricCar;

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
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
 
    //Car car = new Car("Honda", "Accord", 2007, 0, CarType.COUPE);
    DieselCar car = new DieselCar("Honda", "Accord", 2007, 0, CarType.CONVERTIBLE);

		session.save(car);
 
    session.getTransaction().commit();
    
    Query query = session.createQuery("from Car");
    
    List<DieselCar> list = query.getResultList();

    for (DieselCar c: list) {
      System.out.println(c.getCarId());
    }

		session.close();
	}
}
