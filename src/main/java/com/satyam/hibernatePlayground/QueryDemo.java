package com.satyam.hibernatePlayground;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.satyam.hibernatePlayground.entity.Customer;

/**
 * Hello world!
 *
 */
public class QueryDemo {
	public static void main(String[] args) {
		// Connecting to the database
		// This line will create a connection to database using "hibernate.cfg.xml"  
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		// Creating session object to work with database
		Session session = factory.getCurrentSession();

		try {
 
			session.beginTransaction();
			
			List<Customer> customers = session.createQuery("FROM Customer", Customer.class).getResultList();
			
			for (Customer customer : customers) {
				System.out.println(customer);
			}
			// Committing the changes to database
			session.getTransaction().commit();
		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}
}
