package com.company.repositories;

import com.company.models.Activity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityRepository {
    private final List<Activity> activities = new ArrayList<>();

    public List<Activity> getAll() {
        return activities;
    }

    public List<Activity> getActivitiesByUser(int userId) {
        return activities.stream().filter(a -> a.getUserId() == userId).collect(Collectors.toList());
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}
