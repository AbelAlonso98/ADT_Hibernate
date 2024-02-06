package tools;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ejercicio3.Productos;

public class UpdateExample {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query<Productos> q = session.createQuery(
				"update Productos set descripcion = :descripcion where id = :id",
				Productos.class);
		q.setParameter("descripcion", "La descripcion que quieras");
		q.setParameter("id", 70);
		
		int affectedRows = q.executeUpdate();
		System.out.printf("Affected rows: %d%n", affectedRows);
		
		
		session.getTransaction().commit();
		session.close();

	}

}
