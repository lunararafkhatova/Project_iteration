package com.company.controllers.interfaces;

import com.company.models.Activity;
import com.company.models.ActivityFullDTO;
import java.util.List;

public interface IActivityController {
    String createActivity(int userId, int typeId, String name, int duration, String date, String role);
    String deleteActivity(int id, String role);
    List<Activity> getAllActivities();
    List<ActivityFullDTO> getFullActivities();
}

