package com.company.controllers;

import com.company.models.Activity;
import com.company.models.ActivityType;
import com.company.repositories.ActivityRepository;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class ActivityController {

    private final ActivityRepository repository;

    public ActivityController(ActivityRepository repository) {
        this.repository = repository;
    }
    public String createActivity(int userId, int typeId, String name, int duration, String dateStr) {
        try {
            Activity activity = new Activity();
            activity.setUserId(userId);
            activity.setName(name);
            activity.setDuration(duration);
            activity.setActivityDate(LocalDate.parse(dateStr));

            ActivityType type = new ActivityType();
            type.setId(typeId);
            activity.setType(type);

            boolean created = repository.create(activity);

            return created ? "Activity was created successfully!" : "Activity creation failed!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    public List<Activity> getAllActivities() {
        return repository.getAll();
    }

    public void printWeeklyStats(int userId) {
        List<Activity> activities = repository.getAll();

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int currentWeek = LocalDate.now().get(weekFields.weekOfWeekBasedYear());

        int totalMinutes = activities.stream()
                .filter(a -> a.getUserId() == userId)
                .filter(a -> a.getActivityDate()
                        .get(weekFields.weekOfWeekBasedYear()) == currentWeek)
                .mapToInt(Activity::getDuration)
                .sum();

        System.out.println(
                "Total minutes this week for user " + userId + ": " + totalMinutes
        );
    }
    public String deleteActivity(int id) {
        boolean deleted = repository.delete(id);
        return deleted ? "Activity was deleted successfully!" : "Activity with ID " + id + " not found.";
    }
}