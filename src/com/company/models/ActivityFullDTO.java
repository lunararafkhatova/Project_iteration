package com.company.models;

import java.time.LocalDate;

public class ActivityFullDTO {

    private int activityId;
    private String userName;
    private LocalDate activityDate;
    private String activityTypeName;
    private String categoryName;
    private int durationMin;

    public ActivityFullDTO(int activityId, String userName, LocalDate activityDate,
                           String activityTypeName, String categoryName,
                           int durationMin) {

        this.activityId = activityId;
        this.userName = userName;
        this.activityDate = activityDate;
        this.activityTypeName = activityTypeName;
        this.categoryName = categoryName;
        this.durationMin = durationMin;
    }

    @Override
    public String toString() {
        return "Activity#" + activityId +
                " | user=" + userName +
                " | date=" + activityDate +
                " | type=" + activityTypeName +
                " | category=" + categoryName +
                " | duration=" + durationMin;
    }
}
