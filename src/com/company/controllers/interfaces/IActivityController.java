package com.company.controllers.interfaces;

import com.company.models.Activity;
import com.company.models.ActivityFullDTO;

import java.util.List;

public interface IActivityController {

    String createActivity(int userId, int typeId, String name, int duration, String date);

    List<Activity> getAllActivities();

    String deleteActivity(int id, String role);

    List<ActivityFullDTO> getFullActivities();

    void showBasicStats();

    List<String> getActivitiesByCategory(int categoryId);

    String getleaderactivity();
}
