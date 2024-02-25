package mongoDB;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.mongodb.ConnectionString;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import mappedClasses.Empleados;
import tools.HibernateUtil;

public class Ejercicio5_18 {
	static MongoClient mc = MongoClients.create(new ConnectionString("mongodb://localhost:27017"));
	static MongoDatabase db = mc.getDatabase("mibasedatos");

	public static void main(String[] args) {

		showColection("libros");
		createJSONfromCollection("libros");

	}

	private static void showColection(String name) {
		MongoCollection<Document> collection = db.getCollection(name);
		FindIterable<Document> cursor = collection.find();
		MongoCursor<Document> iterator = cursor.cursor();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	private static void insertEmpleadosFromMySQL() {
		db.createCollection("empleadosmysql");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<Empleados> q1 = session.createQuery("from Empleados", Empleados.class);
		List<Empleados> empleados = q1.list();
		empleados.forEach(e -> {
			insertDocument("empleadosmysql", getDocument(e));
		});
		session.getTransaction().commit();
		session.close();
	}

	private static void insertDocument(String c, Document d) {
		try {
			MongoCollection<Document> collection = db.getCollection(c);
			collection.insertOne(d);
		} catch (MongoWriteException e) { // important to catch it
			System.err.printf("A document with _id: %s already exists%n", d.get("_id"));
		}
	}

	private static Document getDocument(Empleados e) {
		Document d = new Document();
		d.put("_id", e.getEmpNo());
		d.put("apellido", e.getApellido());
		d.put("oficio", e.getOficio());
		d.put("dir", e.getDir());
		d.put("fechaAlt", e.getFechaAlt());
		d.put("salario", e.getSalario());
		d.put("comisio", e.getComision());
		d.put("deptNo", e.getDeptNo());
		return d;
	}

	private static void createJSONfromCollection(String c) {
		MongoCollection<Document> collection = db.getCollection(c);
		FindIterable<Document> cursor = collection.find();
		MongoCursor<Document> iterator = cursor.cursor();
		try (FileWriter out = new FileWriter("C:\\Users\\Abel Alonso\\Desktop\\Entrega ADT\\" + c + ".json")) {
			out.write("{\"" + c + "\": [\n");
			while (iterator.hasNext()) {
				out.write(iterator.next().toJson());
				if (iterator.hasNext())
					out.append(",\n");
			}
			out.write("\n]}");
		} catch (IOException e) {
			System.err.println("IN/OUT error while writing file.");
			e.printStackTrace();
		}
	}

	private static void increaseSalarioEmpleados() {

	}

}
