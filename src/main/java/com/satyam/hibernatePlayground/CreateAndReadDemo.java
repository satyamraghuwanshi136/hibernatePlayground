package com.satyam.hibernatePlayground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.satyam.hibernatePlayground.entity.Customer;

/**
 * Hello world!
 *
 */
public class CreateAndReadDemo {
	public static void main(String[] args) {
		// Connecting to the database
		// This line will create a connection to database using "hibernate.cfg.xml"  
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		// Creating session object to work with database
		Session session = factory.getCurrentSession();

		try {
    	    // Creating the customer
			Customer newCustomer = new Customer("satyam", "raghuwanshi", "satyam@gmail.com");
			
			session.beginTransaction();
			// Saving the customer in the database
			session.persist(newCustomer);
			// Printing simple console message for debugging purpose
			System.out.println("saving a customer");
			
			// Committing the changes to database
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// Reading the customer from database
			Customer tempCustomer = session.get(Customer.class, newCustomer.getId());			
			System.out.println(tempCustomer);
			
			// Committing the changes to database
			session.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}
}
