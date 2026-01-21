package com.company.controllers;

import com.company.models.ActivityType;
import com.company.repositories.ActivityTypeRepository;

import java.util.List;

public class ActivityTypeController {

    private final ActivityTypeRepository repository;

    public ActivityTypeController(ActivityTypeRepository repository) {
        this.repository = repository;
    }

    public List<ActivityType> getAllTypes() {
        return repository.getAll();
    }
}