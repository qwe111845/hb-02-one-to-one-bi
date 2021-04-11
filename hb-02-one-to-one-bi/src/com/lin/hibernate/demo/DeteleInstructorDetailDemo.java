package com.lin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lin.hibernate.demo.entity.Instructor;
import com.lin.hibernate.demo.entity.InstructorDetail;

public class DeteleInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				                 .configure("hibernate.cfg.xml")
				                 .addAnnotatedClass(Instructor.class)
				                 .addAnnotatedClass(InstructorDetail.class)
				                 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating new Instruct object...");

			session.beginTransaction();
			
			int theId = 4;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
				
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			System.out.println("tempInstructor: " + tempInstructorDetail.getInstructor());
			
			session.delete(tempInstructorDetail);
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} 
		catch (NullPointerException error) {
			error.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
