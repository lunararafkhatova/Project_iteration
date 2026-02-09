package com.company.controllers.interfaces;

import com.company.models.ActivityType;
import java.util.List;

public interface IActivityTypeController {
    List<ActivityType> getAllTypes();
    List<ActivityType> getTypesByCategory(int categoryId);
}