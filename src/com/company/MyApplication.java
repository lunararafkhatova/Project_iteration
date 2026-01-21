package com.company;

import com.company.controllers.ActivityController;
import com.company.controllers.ActivityTypeController;
import com.company.controllers.interfaces.IUserController;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {

    private final Scanner scanner = new Scanner(System.in);

    private final IUserController userController;
    private final ActivityController activityController;
    private final ActivityTypeController typeController;

    public MyApplication(
            IUserController userController,
            ActivityController activityController,
            ActivityTypeController typeController
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
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-7): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        getAllUsersMenu();
                        break;
                    case 2:
                        getUserByIdMenu();
                        break;
                    case 3:
                        createUserMenu();
                        break;
                    case 4:
                        getAllActivitiesMenu();
                        break;
                    case 5:
                        getAllActivityTypesMenu();
                        break;
                    case 6: createActivityMenu(); break;
                    case 7: deleteActivityMenu(); break;
                    default:
                        return;
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

        System.out.println("Enter Activity Type ID (from option 5):");
        int typeId = scanner.nextInt();

        System.out.println("Enter Activity Name:");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.println("Enter Duration (minutes):");
        int duration = scanner.nextInt();

        System.out.println("Enter Date (YYYY-MM-DD):");
        String date = scanner.next();

        String result = activityController.createActivity(userId, typeId, name, duration, date);
        System.out.println(result);
    }
    public void deleteActivityMenu() {
        System.out.println("Enter Activity ID to delete:");
        try {
            int id = scanner.nextInt();
            String result = activityController.deleteActivity(id);
            System.out.println(result);
        } catch (InputMismatchException e) {
            System.out.println("ID must be a number!");
            scanner.nextLine();
        }
    }
}
