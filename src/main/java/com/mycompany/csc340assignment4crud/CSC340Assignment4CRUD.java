package com.mycompany.csc340assignment4crud;

/**
 * @author mitch
 */

import java.util.Scanner;

public class CSC340Assignment4CRUD {

    public static void main(String[] args) {
        
        MongoDB app = new MongoDB();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add new data");
            System.out.println("2. Display existing data");
            System.out.println("3. Update existing data");
            System.out.println("4. Delete existing data");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    app.promptForNewData();
                    break;
                case 2:
                    app.displayExistingData();
                    break;
                case 3:
                    app.updateExistingData();
                    break;
                case 4:
                    app.deleteExistingData();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    app.mongoClient.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
    }
}
