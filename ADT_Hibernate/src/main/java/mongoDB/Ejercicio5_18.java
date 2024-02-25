package mongoDB;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Ejercicio5_18 {

	public static void main(String[] args) {
//		Creating a mongodb client
		MongoClient mc = MongoClients.create(new ConnectionString("mongodb://localhost:27017"));

		MongoDatabase db = mc.getDatabase("mibasesdatos");
		mc.listDatabases().forEach(System.out::println);

	}

}
