package com.company.models;

import java.time.LocalDate;

public class Activity {
    private int id;
    private int userId;
    private ActivityType activityType;
    private LocalDate activityDate;
    private int durationMin;

    public Activity(int id, int userId, ActivityType activityType, LocalDate activityDate, int durationMin) {
        this.id = id;
        this.userId = userId;
        this.activityType = activityType;
        this.activityDate = activityDate;
        this.durationMin = durationMin;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public ActivityType getActivityType() { return activityType; }
    public LocalDate getActivityDate() { return activityDate; }
    public int getDurationMin() { return durationMin; }
}
