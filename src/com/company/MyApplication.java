package com.company;

import com.company.controllers.ActivityController;
import com.company.controllers.ActivityTypeController;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.IUserController;
import com.company.repositories.ActivityRepository;
import com.company.repositories.ActivityTypeRepository;
import com.company.repositories.UserRepository;

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
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Enter option (1-5): ");
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1: getAllUsersMenu(); break;
                    case 2: getUserByIdMenu(); break;
                    case 3: createUserMenu(); break;
                    case 4: getAllActivitiesMenu(); break;
                    case 5: getAllActivityTypesMenu(); break;
                    default: return;
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
        System.out.println(typeController.getAllTypes());
    }
}
