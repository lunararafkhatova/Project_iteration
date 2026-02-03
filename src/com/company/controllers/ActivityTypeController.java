package com.company.controllers;

import com.company.controllers.interfaces.IActivityTypeController;
import com.company.models.ActivityType;
import com.company.repositories.interfaces.IActivityTypeRepository;


import java.util.List;

public class ActivityTypeController implements IActivityTypeController {

    private final IActivityTypeRepository repository;

    public ActivityTypeController(IActivityTypeRepository repository) {
        this.repository = repository;
    }

    public List<ActivityType> getAllTypes() {
        return repository.getAll();
    }
}