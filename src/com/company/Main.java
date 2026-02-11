package com.company;

import com.company.controllers.ActivityController;
import com.company.controllers.ActivityTypeController;
import com.company.controllers.ActivityCategoryController;
import com.company.controllers.UserController;
import com.company.controllers.interfaces.IActivityController;
import com.company.controllers.interfaces.IActivityTypeController;
import com.company.controllers.interfaces.IActivityCategoryController;
import com.company.controllers.interfaces.IUserController;
import com.company.data.PostgresDB;
import com.company.data.interfaces.IDB;
import com.company.repositories.ActivityRepository;
import com.company.repositories.ActivityTypeRepository;
import com.company.repositories.ActivityCategoryRepository;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IActivityRepository;
import com.company.repositories.interfaces.IUserRepository;
import com.company.repositories.interfaces.IActivityTypeRepository;
import com.company.repositories.interfaces.IActivityCategoryRepository;


import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        IDB db = PostgresDB.getInstance(
                "jdbc:postgresql://localhost:5432",
                "postgres",
                "0000",
                "somedb"
        );

        Connection conn = db.getConnection();

        IUserRepository userRepo = new UserRepository(db);
        IUserController userController = new UserController(userRepo);

        IActivityRepository activityRepo = new ActivityRepository(conn);
        IActivityController activityController = new ActivityController(activityRepo);

        IActivityTypeRepository typeRepo = new ActivityTypeRepository(conn);
        IActivityTypeController typeController = new ActivityTypeController(typeRepo);

        IActivityCategoryRepository categoryRepo = new ActivityCategoryRepository(conn);
        IActivityCategoryController categoryController = new ActivityCategoryController(categoryRepo);

        MyApplication app = new MyApplication(
                userController,
                activityController,
                typeController,
                categoryController
        );

        app.start();
        db.close();
    }
}