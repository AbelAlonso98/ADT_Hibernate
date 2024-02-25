package mongoDB;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

		System.out.println(
				"The entire exercise is splitted in methods, for testing you should execute them all separately:");
		System.out.println("\tIf you want to load Empleados(MySQL) into MongoDB write: load");
		System.out.println("\tFirst method (showCollection) write: show nameOfCollection");
		System.out.println("\t\t If you want to see all collections available write: collections");
		System.out.println("\tSecond method (createJSONfromCollection) write: json nameOfCollection");
		System.out.println("\tThird method (increaseSalarioEmpleados) write: inc");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String command = "";
		while (true) {
			System.out.print(">");
			try {
				command = in.readLine();
				switch (command.split(" ")[0]) {
				case "load":
					insertEmpleadosFromMySQL();
					break;
				case "show":
					showColection(command.split(" ")[1]);
					break;
				case "collections":
					availableCollections();
					break;
				case "json":
					System.out.printf("Write a path for the file: %s.json. It should end with \\%n", command.split(" ")[1]);
					System.out.println("F.E.: C:\\Users\\YourUser\\Desktop\\");
					createJSONfromCollection(command.split(" ")[1], in.readLine());
					break;
				case "inc":
					increaseSalarioEmpleados();
					break;
				default:
					System.err.println("UNKNOWN COMMAND");
					break;
				}
			} catch (IOException e) {
				System.err.println("IN/OUT ERROR.");
				e.printStackTrace();
			}
		}

	}

	private static void showColection(String name) {
		MongoCollection<Document> collection = db.getCollection(name);
		if (collection.estimatedDocumentCount() == 0)
			System.out.println("This collection does not exists or is empty");
		else {
			FindIterable<Document> cursor = collection.find();
			MongoCursor<Document> iterator = cursor.cursor();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
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

	private static void insertDocument(String collection, Document d) {
		try {
			MongoCollection<Document> c = db.getCollection(collection);
			c.insertOne(d);
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

	private static void createJSONfromCollection(String collectionName, String path) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		FindIterable<Document> cursor = collection.find();
		MongoCursor<Document> iterator = cursor.cursor();
		try (FileWriter out = new FileWriter(path + collectionName + ".json")) {
			out.write("{\"" + collectionName + "\": [\n");
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
		System.out.println("=========================EMPLEADOS BEFORE RAISE:=========================");
		showEmpleados();
		MongoCollection<Document> collection = db.getCollection("empleadosmysql");
		Document filter = new Document("oficio", "EMPLEADO");
		Document change = new Document("salario", 100);
		Document updateQuery = new Document("$inc", change);
		collection.updateMany(filter, updateQuery);
		System.out.println("\n\n=========================EMPLEADOS AFTER RAISE:=========================");
		showEmpleados();

	}

	private static void showEmpleados() {
		MongoCollection<Document> collection = db.getCollection("empleadosmysql");
		Document query2 = new Document("oficio", "EMPLEADO");
		FindIterable<Document> cursor = collection.find(query2);
		MongoCursor<Document> iterator = cursor.cursor();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	private static void availableCollections() {
		db.listCollectionNames().forEach(System.out::println);
	}

}
