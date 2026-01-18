package com.company;

import com.company.controllers.UserController;
import com.company.controllers.ActivityController;
import com.company.controllers.ActivityTypeController;
import com.company.controllers.interfaces.IUserController;

import java.util.Scanner;

/**
 *
 */
public class MyApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final UserController userController;
    private final ActivityController activityController;
    private final ActivityTypeController typeController;

    public MyApplication(UserController userController,
                         ActivityController activityController,
                         ActivityTypeController typeController) {
        this.userController = userController;
        this.activityController = activityController;
        this.typeController = typeController;
    }



    private void mainMenu() {
        System.out.println("\n=== My Application ===");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by ID");
        System.out.println("3. Create user");
        System.out.println("4. Get all activities");
        System.out.println("5. Create activity");
        System.out.println("6. Get all activity types");
        System.out.println("0. Exit");
        System.out.print("Enter option (0-6): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> System.out.println(userController.getAllUsers());
                    case 2 -> {
                        System.out.print("Enter user ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(userController.getUser(id));
                    }
                    case 3 -> {
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter surname: ");
                        String surname = scanner.nextLine();
                        System.out.print("Enter gender (male/female): ");
                        String gender = scanner.nextLine();
                        System.out.println(userController.createUser(name, surname, gender));
                    }
                    case 4 -> System.out.println(activityController.getAllActivities());
                    case 5 -> {
                        System.out.print("Enter user ID: ");
                        int userId = scanner.nextInt();
                        System.out.print("Enter activity type ID: ");
                        int typeId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        System.out.println(activityController.createActivity(userId, typeId, date));
                    }
                    case 6 -> System.out.println(typeController.getAllTypes());
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
            System.out.println("*********");
        }
    }

    public static void main(String[] args) {
        UserController userController = new UserController(); // Подставь свои репозитории
        ActivityController activityController = new ActivityController();
        ActivityTypeController typeController = new ActivityTypeController();

        MyApplication app = new MyApplication(userController, activityController, typeController);
        app.start();
    }
}