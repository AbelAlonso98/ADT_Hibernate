package tools;

import org.hibernate.Session;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import mappedClasses.*;

public class DeleteExample {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		// Deprecated ways
		// Row query execution
//		Query q = session.createQuery(
//				"delete Empleados e where e.deptNo = :dep");
//		q.setParameter("dep", 8);
//		int affectedRows = q.executeUpdate();
//		System.out.printf("Affected rows: %d%n", affectedRows);
		
		
		// Using Criteria
//		CriteriaBuilder cb = session.getCriteriaBuilder();
//		CriteriaDelete<Empleados> delete = cb.createCriteriaDelete(Empleados.class);
//		Root root = delete.from(Empleados.class);
//		delete.where(cb.equal(root.get("apellido"), "alonso"));
//		session.createQuery(delete).executeUpdate();
//		
//		
//		// Recommended way using find and then delete.
		Empleados e = session.find(Empleados.class, (byte) 10);
		if(e!=null)
			session.remove(e);
		else
			System.err.println("No existe empleado con id 8");
		
		
		session.getTransaction().commit();
		session.close();

	}

}
