package com.mycompany.csc340assignment4crud;

/**
 * @author mitch
 */

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Scanner;

public class MongoDB {
    
    private static final String DATABASE_NAME = "CSC340Assignment4DB";
    private static final String COLLECTION_NAME = "CSC340Assignment4Collection";

    final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MongoDB() {
        String connectionString = "mongodb+srv://rmhaws:Sundevil2018@cluster0.nzqumxi.mongodb.net/?retryWrites=true&w=majority";
        
        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(connectionString)).serverApi(serverApi).build();
        
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(DATABASE_NAME);
        collection = database.getCollection(COLLECTION_NAME);
    }

    public void promptForNewData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter occupation: ");
        String occupation = scanner.nextLine();

        Document document = new Document("name", name)
                .append("age", age)
                .append("occupation", occupation);
        collection.insertOne(document);
        System.out.println("Data added successfully.");
    }

    public void displayExistingData() {
        System.out.println("Existing data:");
        for (Document document : collection.find()) {
            System.out.println(document.toJson());
        }
    }

    public void updateExistingData() {
        displayExistingData();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the person you want to update: ");
        String name = scanner.nextLine();
        System.out.print("Enter the new age: ");
        int newAge = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the new occupation: ");
        String newOccupation = scanner.nextLine();

        collection.updateOne(Filters.eq("name", name),
                Updates.combine(
                        Updates.set("age", newAge),
                        Updates.set("occupation", newOccupation)
                ));
        System.out.println("Data updated successfully.");
    }

    public void deleteExistingData() {
        displayExistingData();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the person you want to delete: ");
        String name = scanner.nextLine();
        collection.deleteOne(Filters.eq("name", name));
        System.out.println("Data deleted successfully.");
    }
    
}
