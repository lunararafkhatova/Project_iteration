package com.company.repositories.interfaces;

import com.company.models.Activity;
import com.company.models.ActivityFullDTO;

import java.util.List;

public interface IActivityRepository {

    List<Activity> getAll();

    boolean create(Activity activity);

    boolean delete(int id);

    List<ActivityFullDTO> getFullActivities();

    List<String> getActivitiesByCategory(int categoryId);
}
