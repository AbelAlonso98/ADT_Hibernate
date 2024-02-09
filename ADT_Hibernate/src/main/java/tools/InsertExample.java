package tools;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import jakarta.persistence.PersistenceException;
import mappedClasses.*;

public class InsertExample {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
//		Departamentos dep = new Departamentos((byte) 8, "dep", "loc");
//		session.persist(dep);		
		
		Empleados e = new Empleados(12, "jimenez", "vago", 80, new Date(new java.util.Date().getTime()), 6.0f, 1.0f, 8);
		try {
			session.persist(e);
			session.getTransaction().commit();
			System.out.println("Cliente introducido correcatemente.");
			session.close();
		} catch (ConstraintViolationException ex) {
			System.err.println("El cliente introducido ya existe.");
			session.getTransaction().rollback();
		} catch (PersistenceException ex) {
			System.err.println("Error de persistencia de datos");
			ex.printStackTrace();
		}
		



	}

}
