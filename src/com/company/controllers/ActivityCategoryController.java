package com.company.controllers;

import com.company.controllers.interfaces.IActivityCategoryController;
import com.company.models.ActivityCategory;
import com.company.repositories.interfaces.IActivityCategoryRepository;

import java.util.List;

public class ActivityCategoryController implements IActivityCategoryController {

    private final IActivityCategoryRepository repository;

    public ActivityCategoryController(IActivityCategoryRepository repository) {
        this.repository = repository;
    }

    public List<ActivityCategory> getAllCategories() {
        return repository.getAll();
    }
}