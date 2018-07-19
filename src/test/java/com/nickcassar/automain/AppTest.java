package com.nickcassar.automain;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.nickcassar.automain.enums.CarType;
import com.nickcassar.automain.models.Car;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
 
		Car car = new Car("Honda", "Accord", 2007, 0, CarType.COUPE);
		session.save(car);
 
		session.getTransaction().commit();
		session.close();
	}
}
