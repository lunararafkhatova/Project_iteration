package com.company.controllers;

import com.company.controllers.interfaces.IActivityController;
import com.company.models.Activity;
import com.company.models.ActivityType;
import com.company.models.ActivityFullDTO;
import com.company.repositories.interfaces.IActivityRepository;

import java.time.LocalDate;
import java.util.List;

public class ActivityController implements IActivityController {

    private final IActivityRepository repository;

    public ActivityController(IActivityRepository repository) {
        this.repository = repository;
    }

    public String createActivity(int userId, int typeId, String name, int duration, String dateStr) {

        if (name == null || name.isBlank()) {
            return "Validation error: Activity name is required.";
        }

        if (duration <= 0) {
            return "Validation error: Duration must be greater than 0.";
        }

        try {
            LocalDate date = LocalDate.parse(dateStr);

            Activity activity = new Activity();
            activity.setUserId(userId);
            activity.setName(name);
            activity.setDuration(duration);
            activity.setActivityDate(date);

            ActivityType type = new ActivityType();
            type.setId(typeId);
            activity.setType(type);

            boolean created = repository.create(activity);

            return created
                    ? "Activity was created successfully!"
                    : "Activity creation failed!";

        } catch (Exception e) {
            return "Error: Invalid date format (use YYYY-MM-DD)";
        }
    }

    public List<Activity> getAllActivities() {
        return repository.getAll();
    }

    public String deleteActivity(int id, String role) {

        if (!role.equalsIgnoreCase("ADMIN")) {
            return "Access denied!";
        }

        boolean deleted = repository.delete(id);

        return deleted
                ? "Activity was deleted successfully!"
                : "Activity with ID " + id + " not found.";
    }

    public List<ActivityFullDTO> getFullActivities() {
        return repository.getFullActivities();
    }

    public void showBasicStats() {
        List<ActivityFullDTO> activities = repository.getFullActivities();

        int totalTime = activities.stream().mapToInt(a -> a.getDurationMin()).sum();
        long userCount = activities.stream().map(a -> a.getUserName()).distinct().count();

        System.out.println("Total minutes tracked: " + totalTime);
        System.out.println("Unique users active: " + userCount);
    }

    public List<String> getActivitiesByCategory(int categoryId) {
        return repository.getActivitiesByCategory(categoryId);
    }
}
