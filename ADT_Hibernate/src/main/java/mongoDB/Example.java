package mongoDB;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Example {
	public static void main(String[] args) {
		// Creating a mongodb client
		MongoClient mc = MongoClients.create(new ConnectionString("mongodb://localhost:27017"));
		MongoDatabase db = mc.getDatabase("mibasedatos");

		// Listing all databases
//		mc.listDatabases().forEach(System.out::println);

		// Inserting a document into our collection
//		try {
//			MongoCollection<Document> collection = db.getCollection("test");
//			Document document = new Document();
//			document.put("_id", "1");
//			document.put("name", "Abel");
//			document.put("surname", "Alonso");
//			document.put("age", 25);
//			collection.insertOne(document);
//		} catch (MongoWriteException e) { // important to catch it
//			System.err.println("A document with that _id already exists");
//		}
		
		// Updating a document
//		MongoCollection<Document> collection = db.getCollection("test");
//		Document existing = new Document();
//		existing.put("name", "Abel");
//		Document newD = new Document();
//		newD.put("name", "Kike");
//		Document updateQuery = new Document();
//		updateQuery.put("$set", newD);
//		collection.updateOne(existing, updateQuery);
		
		// Read a document
//		MongoCollection<Document> collection = db.getCollection("test");
////		Document query = new Document();
////		query.put("name", "Kike");
//		Document query2 = new Document("name", "Kike");
//		FindIterable<Document> cursor = collection.find(query2);
//		MongoCursor<Document> iterator = cursor.cursor();
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
		
		// Delete a document
//		MongoCollection<Document> collection = db.getCollection("test");
//		Document query = new Document("name", "Kike");
//		collection.deleteOne(query);
	}

}
