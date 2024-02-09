package ejercicio6;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import jakarta.persistence.PersistenceException;
import mappedClasses.Productos;
import tools.HibernateUtil;

public class Ejercicio6_productos {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// Insertar productos
		Productos p1 = new Productos(1, "Pienso Acana Light&Fit S", 8, 2, new BigDecimal("68.75"), null);
		Productos p2 = new Productos(2, "Pienso Acana Light&Fit M", 8, 2, new BigDecimal("68.75"), null);
		Productos p3 = new Productos(3, "Pienso Acana Light&Fit L", 8, 2, new BigDecimal("68.75"), null);
		try {
			session.persist(p1);
			session.persist(p2);
			session.persist(p3);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			System.err.println("El producto introducido ya existe.");
			session.getTransaction().rollback();
		} catch (PersistenceException e) {
			System.err.println("Error de persistencia de datos");
			e.printStackTrace();
		}
		session.beginTransaction();
	}

}
