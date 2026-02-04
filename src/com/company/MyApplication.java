package com.company;

import com.company.controllers.interfaces.IUserController;
import com.company.controllers.interfaces.IActivityController;
import com.company.controllers.interfaces.IActivityTypeController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {

    private final Scanner scanner = new Scanner(System.in);

    private final IUserController userController;
    private final IActivityController activityController;
    private final IActivityTypeController typeController;

    public MyApplication(
            IUserController userController,
            IActivityController activityController,
            IActivityTypeController typeController
    ) {
        this.userController = userController;
        this.activityController = activityController;
        this.typeController = typeController;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create user");
        System.out.println("4. Get all activities");
        System.out.println("5. Get all activity types");
        System.out.println("6. Create activity");
        System.out.println("7. Delete activity");
        System.out.println("8. Get full activities (JOIN)");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-8): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1 -> getAllUsersMenu();
                    case 2 -> getUserByIdMenu();
                    case 3 -> createUserMenu();
                    case 4 -> getAllActivitiesMenu();
                    case 5 -> getAllActivityTypesMenu();
                    case 6 -> createActivityMenu();
                    case 7 -> deleteActivityMenu();
                    case 8 -> getFullActivitiesMenu();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void getAllUsersMenu() {
        System.out.println(userController.getAllUsers());
    }

    public void getUserByIdMenu() {
        System.out.println("Please enter id");
        int id = scanner.nextInt();
        System.out.println(userController.getUser(id));
    }

    public void createUserMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter surname");
        String surname = scanner.next();
        System.out.println("Please enter gender (male/female)");
        String gender = scanner.next();
        System.out.println(userController.createUser(name, surname, gender));
    }

    public void getAllActivitiesMenu() {
        System.out.println(activityController.getAllActivities());
    }

    public void getAllActivityTypesMenu() {
        var types = typeController.getAllTypes();
        for (var type : types) {
            System.out.println(type);
        }
    }

    public void createActivityMenu() {
        System.out.println("Enter User ID:");
        int userId = scanner.nextInt();

        System.out.println("Enter Activity Type ID:");
        int typeId = scanner.nextInt();

        System.out.println("Enter Activity Name:");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("Enter Duration (minutes):");
        int duration = scanner.nextInt();

        System.out.println("Enter Date (YYYY-MM-DD):");
        String date = scanner.next();

        System.out.println("Enter your role (ADMIN or USER):");
        String role = scanner.next();

        String result = activityController.createActivity(
                userId, typeId, name, duration, date, role
        );

        System.out.println(result);
    }

    public void deleteActivityMenu() {
        System.out.println("Enter Activity ID to delete:");
        int id = scanner.nextInt();

        System.out.println("Enter your role (ADMIN or USER):");
        String role = scanner.next();

        String result = activityController.deleteActivity(id, role);
        System.out.println(result);
    }

    public void getFullActivitiesMenu() {
        var activities = activityController.getFullActivities();

        if (activities.isEmpty()) {
            System.out.println("\n>>> No activities found. Check your database tables.");
            return;
        }

        // Рисуем красивую шапку
        System.out.println("\n" + "=".repeat(98));
        System.out.println("                          FULL ACTIVITIES REPORT");
        System.out.println("=".repeat(98));
        System.out.printf("| %-4s | %-15s | %-12s | %-20s | %-15s | %-8s |%n",
                "ID", "USER", "DATE", "TYPE", "CATEGORY", "MINS");
        System.out.println("-".repeat(98));

        // Выводим данные строками
        for (var a : activities) {
            System.out.printf("| %-4d | %-15s | %-12s | %-20s | %-15s | %-8d |%n",
                    a.getActivityId(),
                    a.getUserName(),
                    a.getActivityDate(),
                    a.getActivityTypeName(),
                    a.getCategoryName(),
                    a.getDurationMin());
        }
        System.out.println("=".repeat(98));
    }
}
