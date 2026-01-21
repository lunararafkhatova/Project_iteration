package com.company;

import com.company.controllers.ActivityController;
import com.company.controllers.ActivityTypeController;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.ActivityRepository;
import com.company.repositories.ActivityTypeRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IUserRepository;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB(
                "jdbc:postgresql://localhost:5432",
                "postgres",
                "0000",
                "somedb"
        );

        Connection conn = db.getConnection();

        IUserRepository userRepo = new UserRepository(db);
        IUserController userController = new UserController(userRepo);

        ActivityRepository activityRepo = new ActivityRepository(conn);
        ActivityTypeRepository typeRepo = new ActivityTypeRepository(conn);

        ActivityController activityController = new ActivityController(activityRepo);
        ActivityTypeController typeController = new ActivityTypeController(typeRepo);

        MyApplication app = new MyApplication(
                userController,
                activityController,
                typeController
        );

        app.start();
        db.close();
    }
}
