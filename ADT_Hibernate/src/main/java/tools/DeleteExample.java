package tools;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ejercicio4.Empleados;

public class DeleteExample {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query<Empleados> q = session.createQuery(
				"delete Empleados e where e.departamentos.deptNo = :dep",
				Empleados.class);
		q.setParameter("dep", 20);
		
		int affectedRows = q.executeUpdate();
		System.out.printf("Affected rows: %d%n", affectedRows);
		
		
		session.getTransaction().commit();
		session.close();

	}

}
