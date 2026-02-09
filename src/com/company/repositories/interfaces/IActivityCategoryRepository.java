package com.company.repositories.interfaces;

import com.company.models.ActivityCategory;
import java.util.List;

public interface IActivityCategoryRepository {
    List<ActivityCategory> getAll();
}