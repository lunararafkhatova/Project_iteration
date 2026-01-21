package com.company.controllers;

import com.company.models.Activity;
import com.company.repositories.ActivityRepository;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class ActivityController {

    private final ActivityRepository repository;

    // ✅ ОДИН конструктор
    public ActivityController(ActivityRepository repository) {
        this.repository = repository;
    }

    // ✅ Получение всех activity
    public List<Activity> getAllActivities() {
        return repository.getAll();
    }

    // ✅ Статистика за неделю (на основе БД)
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
}