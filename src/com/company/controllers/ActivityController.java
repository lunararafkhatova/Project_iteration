package com.company.controllers;

import com.company.models.Activity;
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

    public ActivityController() {
        this.repository = new ActivityRepository();
    }

    public void logActivity(Activity activity) {
        repository.addActivity(activity);
    }

    public void printWeeklyStats(int userId) {
        List<Activity> activities = repository.getActivitiesByUser(userId);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int currentWeek = LocalDate.now().get(weekFields.weekOfWeekBasedYear());
        int totalMinutes = activities.stream()
                .filter(a -> a.getActivityDate().get(weekFields.weekOfWeekBasedYear()) == currentWeek)
                .mapToInt(Activity::getDurationMin)
                .sum();

        System.out.println("Total minutes this week for user " + userId + ": " + totalMinutes);
    }

    public List<Activity> getAllActivities() {
        return repository.getAll();
    }

    public boolean createActivity(int userId, int typeId, String date) {
        return false;
    }
}
