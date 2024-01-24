package ejercicio5;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import ejercicio3.Clientes;
import ejercicio3.Ventas;
import tools.HibernateUtil;

public class Ejercicio5 {

	public static void main(String[] args) {
		// Chequeo que los argumentos sean correctos
		if(args.length!=1) {
			System.err.println("Introduce un nombre por argumentos");
			System.exit(0);
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		System.out.println("COMPROBANDO CLIENTE...");
		System.out.println("------------------------------------------");
		
		// Obtenemos el objeto cliente de la base de datos partiendo del nombre que recibimos por args
		Query<Clientes> q1 = session.createQuery("from Clientes where nombre = :nombre", ejercicio3.Clientes.class);
		q1.setParameter("nombre", args[0]);
		List<Clientes> clientes = q1.list();
		if(clientes.size()>1) {
			System.err.println("Se ha encontrado más de un cliente con ese nombre.");
			System.exit(0);
		} else if(clientes.size()<1) {
			System.err.printf("No se ha encontrado ningún cliente de nombre: %s%n", args[0]);
			System.exit(0);
		}
		System.out.printf("Ventas del cliente: %s: %n", args[0]);
		System.out.println("------------------------------------------");
		
		// Obtengo las ventas del cliente obtenido previamente
		Query<Ventas> q2 = session.createQuery("from Ventas where clientes = :cliente", Ventas.class);
		q2.setParameter("cliente", clientes.get(0));
		List<Ventas> ventas = q2.list();
		

		// Creo dos variables para calcular el precio e ir acumulandolo
		BigDecimal importeAcumulado = new BigDecimal(0);
		BigDecimal importe = new BigDecimal(0);
		
		// Recorro las ventas mostrando por pantalla lo necesario
		for(Ventas v: ventas) {
			System.out.printf("Venta: %d ** %s%n", v.getIdventa(), v.getFechaventa());
			System.out.printf("\tProducto: %s%n", v.getProductos().getDescripcion());
			// PVP ES UN BIGDECIMAL ????????????????????????????
			System.out.printf("\tCantidad: %s ** PVP: %s €%n", v.getCantidad(), v.getProductos().getPvp().toString());
			importe = v.getProductos().getPvp().multiply(new BigDecimal(v.getCantidad()));
			importeAcumulado = importeAcumulado.add(importe);
			System.out.printf("\tImporte: %s €%n", importe.toString());
		}
		
		// Imprimo el "resumen"
		System.out.println("------------------------------------------");
		System.out.printf("Numero total de ventas: %d%n", ventas.size());
		System.out.printf("Importe total: %s €%n", importeAcumulado.toString());
		System.out.println("------------------------------------------");
		
		// cierro la sesion
		session.getTransaction().commit();

	}

}
