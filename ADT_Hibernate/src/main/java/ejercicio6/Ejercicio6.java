package ejercicio6;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import ejercicio3.Clientes;
import jakarta.persistence.PersistenceException;
import tools.HibernateUtil;

public class Ejercicio6 {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Insertar clientes
		Clientes c1 = new Clientes((byte) 5, "Cliente 1", "Calle Aleatoria", "Langreo", "666", "000", null);
		Clientes c2 = new Clientes((byte) 6, "Cliente 2", "Calle Aleatoria", "Langreo", "666", "000", null);
		Clientes c3 = new Clientes((byte) 7, "Cliente 3", "Calle Aleatoria", "Langreo", "666", "000", null);
		
		try {
			session.persist(c1);
			session.persist(c2);
			session.persist(c3);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			System.err.println("El cliente introducido ya existe.");
			session.getTransaction().rollback();
		} catch (PersistenceException e) {
			System.err.println("Error de persistencia de datos");
			e.printStackTrace();
		}

	}

}
