package com.company.models;

import java.time.LocalDate;

public class Activity {
    private int id;
    private int userId;
    private String name;
    private int duration;
    private LocalDate activityDate;
    private ActivityType type;

    public Activity() {
    }

    public Activity(int id, int userId, String name, int duration,
                    LocalDate activityDate, ActivityType type) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.duration = duration;
        this.activityDate = activityDate;
        this.type = type;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "-----------------------------------\n" +
                " Activity Details:\n" +
                "-----------------------------------\n" +
                " ID:       " + id + "\n" +
                " User ID:  " + userId + "\n" +
                " Name:     " + name + "\n" +
                " Duration: " + duration + " min\n" +
                " Date:     " + activityDate + "\n" +
                " Type:     " + (type != null ? type.getName() : "None") + "\n" +
                "-----------------------------------";
    }
}