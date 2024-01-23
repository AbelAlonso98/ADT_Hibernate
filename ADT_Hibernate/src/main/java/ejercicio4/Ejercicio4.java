package ejercicio4;

import org.hibernate.Session;

import tools.HibernateUtil;

public class Ejercicio4 {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.getTransaction().commit();
		
		// Es tan simple como ejecutar esta query y recorrer la lista de objetos que devuelve imprimiendo datos
		// para ello se ha generado el metodo toString() en la clase Empleados, de no hacerlo, imprimiria
		// la referencia en memoria del objeto.
		session.createQuery("from Empleados where empNo = 7369", Empleados.class).list().forEach(
				t -> System.out.println(t.toString()));
		session.getTransaction().commit();
	}
	
	

}
