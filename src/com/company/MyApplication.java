package com.company;

import com.company.controllers.interfaces.IUserController;
import com.company.controllers.interfaces.IActivityController;
import com.company.controllers.interfaces.IActivityTypeController;
import com.company.controllers.interfaces.IActivityCategoryController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {

    private String currentRole = "GUEST";
    private static final String ADMIN_PASSWORD = "6767";

    private final Scanner scanner = new Scanner(System.in);

    private final IUserController userController;
    private final IActivityController activityController;
    private final IActivityTypeController typeController;
    private final IActivityCategoryController categoryController;

    public MyApplication(
            IUserController userController,
            IActivityController activityController,
            IActivityTypeController typeController,
            IActivityCategoryController categoryController
    ) {
        this.userController = userController;
        this.activityController = activityController;
        this.typeController = typeController;
        this.categoryController = categoryController;
    }

    public void start() {
        authenticate();

        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {

                    case 1 -> System.out.println(userController.getAllUsers());

                    case 2 -> {
                        System.out.print("Enter user ID: ");
                        int id = scanner.nextInt();
                        System.out.println(userController.getUser(id));
                    }

                    case 3 -> {
                        System.out.print("Enter name: ");
                        String name = scanner.next();
                        System.out.print("Enter surname: ");
                        String surname = scanner.next();
                        System.out.print("Enter gender: ");
                        String gender = scanner.next();
                        System.out.println(userController.createUser(name, surname, gender));
                    }

                    case 4 -> System.out.println(activityController.getAllActivities());

                    case 5 -> typeController.getAllTypes().forEach(System.out::println);

                    case 6 -> {
                        if (currentRole.equalsIgnoreCase("ADMIN")) {
                            createActivityMenu();
                        } else {
                            System.out.println("ACCESS DENIED");
                        }
                    }

                    case 7 -> {
                        if (currentRole.equalsIgnoreCase("ADMIN")) {
                            deleteActivityMenu();
                        } else {
                            System.out.println("ACCESS DENIED");
                        }
                    }

                    case 8 -> getFullActivitiesMenu();

                    case 9 -> {
                        if (currentRole.equalsIgnoreCase("ADMIN")) {
                            activityController.showBasicStats();
                        } else {
                            System.out.println("ACCESS DENIED");
                        }
                    }

                    case 10 -> {
                        if (currentRole.equalsIgnoreCase("USER")) {
                            showActivitiesByCategoryMenu();
                        } else {
                            System.out.println("ACCESS DENIED");
                        }
                    }

                    case 0 -> {
                        return;
                    }

                    default -> System.out.println("Invalid option");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine();
            }

            System.out.println("*************************");
        }
    }

    private void mainMenu() {
        System.out.println("\nSelect option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create user");
        System.out.println("4. Get all activities");
        System.out.println("5. Get all activity types");
        System.out.println("8. Get full activities (JOIN)");

        if (currentRole.equalsIgnoreCase("ADMIN")) {
            System.out.println("6. Create activity");
            System.out.println("7. Delete activity");
            System.out.println("9. Show Quick Stats");
        }

        if (currentRole.equalsIgnoreCase("USER")) {
            System.out.println("10. View activities by category");
        }

        System.out.println("0. Exit");
        System.out.print("Enter option: ");
    }

    private void createActivityMenu() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();

        System.out.print("Enter Activity Type ID: ");
        int typeId = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter Activity Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Duration: ");
        int duration = scanner.nextInt();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.next();

        System.out.println(
                activityController.createActivity(userId, typeId, name, duration, date)
        );
    }

    private void deleteActivityMenu() {
        System.out.print("Enter Activity ID: ");
        int id = scanner.nextInt();
        System.out.println(activityController.deleteActivity(id, currentRole));
    }

    private void getFullActivitiesMenu() {
        var activities = activityController.getFullActivities();

        if (activities.isEmpty()) {
            System.out.println("No activities found");
            return;
        }

        for (var a : activities) {
            System.out.println(
                    a.getActivityId() + " | " +
                            a.getUserName() + " | " +
                            a.getActivityDate() + " | " +
                            a.getActivityTypeName() + " | " +
                            a.getCategoryName() + " | " +
                            a.getDurationMin()
            );
        }
    }

    private void showActivitiesByCategoryMenu() {

        var categories = categoryController.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories found");
            return;
        }

        System.out.println("\nChoose a category:");
        for (var c : categories) {
            System.out.println(c.getId() + ". " + c.getName());
        }

        System.out.print("Enter category ID (0 to cancel): ");
        int categoryId = scanner.nextInt();
        if (categoryId == 0) return;

        var activities = activityController.getActivitiesByCategory(categoryId);
        if (activities.isEmpty()) {
            System.out.println("No activities in this category");
            return;
        }

        System.out.println("\nChoose an activity:");
        for (int i = 0; i < activities.size(); i++) {
            System.out.println((i + 1) + ". " + activities.get(i));
        }

        System.out.print("Enter activity number (0 to cancel): ");
        int choice = scanner.nextInt();
        if (choice == 0) return;

        if (choice < 1 || choice > activities.size()) {
            System.out.println("Invalid choice");
            return;
        }

        String selectedActivity = activities.get(choice - 1);
        System.out.println("\nYou selected activity: " + selectedActivity);
    }

    private void authenticate() {
        System.out.print("Select role (1 - ADMIN, 2 - USER): ");
        try {
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter admin password: ");
                String pass = scanner.next();
                if (pass.equals(ADMIN_PASSWORD)) {
                    currentRole = "ADMIN";
                    System.out.println("Logged in as ADMIN");
                } else {
                    currentRole = "USER";
                    System.out.println("Wrong password. Logged in as USER");
                }
            } else {
                currentRole = "USER";
                System.out.println("Logged in as USER");
            }
        } catch (Exception e) {
            currentRole = "USER";
            scanner.nextLine();
        }
    }
}
