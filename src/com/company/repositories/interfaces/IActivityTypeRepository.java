package com.company.repositories.interfaces;

import com.company.models.ActivityType;
import java.util.List;

public interface IActivityTypeRepository {
    List<ActivityType> getAll();
}

