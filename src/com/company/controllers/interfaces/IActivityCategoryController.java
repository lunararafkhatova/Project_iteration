package com.company.controllers.interfaces;

import com.company.models.ActivityCategory;
import java.util.List;

public interface IActivityCategoryController {
    List<ActivityCategory> getAllCategories();
}