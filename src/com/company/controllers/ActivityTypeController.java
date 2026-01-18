package com.company.controllers;

import com.company.models.ActivityType;
import com.company.repositories.ActivityTypeRepository;

import java.util.List;

public class ActivityTypeController{

    private final ActivityTypeRepository repository;

    public ActivityTypeController(ActivityTypeRepository repository) {
        this.repository = repository;
    }

    public ActivityTypeController() {
        this.repository = new ActivityTypeRepository();
    }

    public List<ActivityType> getAllTypes() {
        return repository.getAll(); // getAll() должен быть в ActivityTypeRepository
    }

    // Если нужно, можно добавить метод для создания нового типа
    public String createType(String name) {
        ActivityType type = new ActivityType(name);
        repository.add(type); // add() должен быть в ActivityTypeRepository
        return "Activity type added";
    }
}

