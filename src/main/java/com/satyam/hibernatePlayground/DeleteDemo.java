package com.satyam.hibernatePlayground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.satyam.hibernatePlayground.entity.Customer;

/**
 * Hello world!
 *
 */
public class DeleteDemo {
	public static void main(String[] args) {
		// Connecting to the database
		// This line will create a connection to database using "hibernate.cfg.xml"  
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		// Creating session object to work with database
		Session session = factory.getCurrentSession();

		try {
 
			session.beginTransaction();
			
			int id = 12;
			Customer customer = session.get(Customer.class, id);
			// We simply call the setter method of a class and it automatically do the work for us
			session.remove(customer);
			
			// Committing the changes to database
			session.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}
}
