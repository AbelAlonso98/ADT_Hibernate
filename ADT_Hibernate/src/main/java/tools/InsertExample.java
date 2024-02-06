package tools;

import org.hibernate.Session;

import ejercicio4.Departamentos;

public class InsertExample {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Departamentos dep = new Departamentos((byte) 8, "NombreDepartamento", "LocalizacionDepartamento");
		session.persist(dep);		
		
		session.getTransaction().commit();
		session.close();


	}

}
