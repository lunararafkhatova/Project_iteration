package com.company.controllers.interfaces;

import com.company.models.User;

import java.util.List;

public interface IUserController {
    List<User> getAll(String name, String surname, String gender);

    void getById(int id);

    void create(User user);

    void create(String name);

    String getAllUsers();

    String getUser(int id);

    String createUser(String name,String surname,String gender);
}
