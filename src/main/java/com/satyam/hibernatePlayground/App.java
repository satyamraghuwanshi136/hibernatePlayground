package com.satyam.hibernatePlayground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.satyam.hibernatePlayground.entity.Customer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       SessionFactory factory = new Configuration()
    		   				   .configure("hibernate.cfg.xml")
    		   				   .addAnnotatedClass(Customer.class)
    		   				   .buildSessionFactory();
       
       Session session = factory.getCurrentSession();
       
       
       try {
		Customer newCustomer = new Customer("satyam", "raghuwanshi", "satyam@gmail.com");
    	session.beginTransaction();
    	session.persist(newCustomer);
    	System.out.println("saving a customer");
    	session.getTransaction().commit();
    	   
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}finally {
		factory.close();
	}
    }
}
