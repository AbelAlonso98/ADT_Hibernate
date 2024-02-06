package tools;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ejercicio3.Clientes;

public class SelectExample {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		// Si necesitamos buscar por un atributo que no sea Primary Key
		Query<Clientes> q1 = session.createQuery("from Clientes where nombre = :nombre", ejercicio3.Clientes.class);
		q1.setParameter("nombre", args[0]);
		List<Clientes> clientes = q1.list();
		clientes.forEach(c -> {
			System.out.println(c.toString());
		});
		
		// Si necesitamos buscar por Primary Key
		session.get(Clientes.class, (byte) 10);
		
		
		session.getTransaction().commit();
		session.close();

	}

}
