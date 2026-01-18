package com.company.controllers;

import com.company.data.interfaces.IDB;
import com.company.models.User;
import com.company.controllers.interfaces.IUserController;
import com.company.repositories.UserRepository;
import com.company.repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) { // Dependency Injection
        this.repo = repo;
    }

    public UserController() {
        this.repo = new UserRepository(new YourDBImplemention());
    }

    public String createUser(String name, String surname, String gender) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(name, surname, male);

        boolean created = repo.createUser(user);

        return (created ? "User was created!" : "User creation was failed!");
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    @Override
    public List<User> getAll(String name, String surname, String gender) {
        return List.of();
    }

    @Override
    public void getById(int id) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void create(String name) {

    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }

        return response.toString();
    }

    private record YourDBImplemention() implements IDB {
        @Override
        public Connection getConnection() {
            return null;
        }

        @Override
        public void close() {

        }
    }
}
