package tools;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import mappedClasses.*;

public class UpdateExample {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
//		Query<Productos> q = session.createQuery(
//				"update Empleados set apellido= :apellidoNuevo where apellido = :apellidoViejo");
//		q.setParameter("apellidoNuevo", "alonso");
//		q.setParameter("apellidoViejo", "jimenez");
//		int affectedRows = q.executeUpdate();
//		System.out.printf("Affected rows: %d%n", affectedRows);
		
//		CriteriaBuilder cb = session.getCriteriaBuilder();
//		CriteriaUpdate<Empleados> update = cb.createCriteriaUpdate(Empleados.class);
//		Root root = update.from(Empleados.class);
//		update.set("apellido", "jimenez");
//		update.where(cb.equal(root.get("apellido"), "alonso"));
//		session.createQuery(update).executeUpdate();
		
		
		Empleados empleadoAActualizar = session.get(Empleados.class, 10);
		empleadoAActualizar.setApellido("garcia");
		session.persist(empleadoAActualizar);
		
		
		session.getTransaction().commit();
		session.close();

	}

}
