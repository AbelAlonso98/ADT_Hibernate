package ejercicio6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalTime;
import java.sql.Date;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import ejercicio3.Clientes;
import ejercicio3.Productos;
import ejercicio3.Ventas;
import jakarta.persistence.PersistenceException;
import tools.HibernateUtil;

public class Ejercicio6_ventas {

	public static void main(String[] args) {

		// Creamos la conexion
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		// Leemos por consola los datos de la venta que queremos
		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Introduce id de la venta a crear");
			int id = Integer.parseInt(in.readLine());

			Query<Productos> q3 = session.createQuery("from Ventas where idventa = :id",
					ejercicio3.Productos.class);
			q3.setParameter("id", id);
			List<Productos> resVent = q3.list();
			if (resVent.size() > 0) {
				System.err.println("Ya existe esa venta");
				System.exit(0);
			}
			
			// Una vez recibamos el nombre del producto miramos si realmente existe
			System.out.println("Introduce nombre del producto a vender");
			String productName = in.readLine();
			Query<Productos> q1 = session.createQuery("from Productos where descripcion = :descripcion",
					ejercicio3.Productos.class);
			q1.setParameter("descripcion", productName);
			List<Productos> resProd = q1.list();
			if (resProd.size() < 1) {
				System.err.println("No existe producto con ese nombre");
				System.exit(0);
			}

			
			// Una vez recibamos la cantidad, comprobamos que es valida 
			// (stockactual — cantidad>= stockminimo) 
			System.out.println("Introduce cantidad del producto");
			int productCant = Integer.parseInt(in.readLine());
			if (productCant < 0 || resProd.get(0).getStockactual() - productCant >= resProd.get(0).getStockminimo()) {
				System.err.println("No se puede vender esa cantidad");
				System.exit(0);
			}
			System.out.println("Introduce nombre del cliente");
			String clientName = in.readLine();
			Query<Clientes> q2 = session.createQuery("from Clientes where nombre = :nombre", ejercicio3.Clientes.class);
			q2.setParameter("nombre", clientName);
			List<Clientes> resCli = q2.list();
			if (resCli.size() < 1) {
				System.err.println("No existe cliente con ese nombre");
				System.exit(0);
			}

			// Calculo la fecha de hoy en un objeto Date @author Charles
			Ventas v = new Ventas((short) id, resProd.get(0), resCli.get(0), new Date(new java.util.Date().getTime()),
					(byte) productCant);

			session.persist(v);
			session.getTransaction().commit();
		} catch (NumberFormatException e) {
			System.err.println("ID y cantidad han de ser números");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			System.err.println("El cliente introducido ya existe.");
			session.getTransaction().rollback();
		} catch (PersistenceException e) {
			System.err.println("Error de persistencia de datos");
			e.printStackTrace();
		}

	}

}
