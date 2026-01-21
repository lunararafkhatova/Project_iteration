package com.company.repositories;

import com.company.models.Activity;
import com.company.models.ActivityType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository {

    private final Connection connection;

    public ActivityRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Activity> getAll() {
        List<Activity> activities = new ArrayList<>();

        String sql = """
            SELECT a.id,
                   a.user_id,
                   a.activity_type_id,
                   a.name,
                   a.duration,
                   a.activity_date,
                   t.name AS type_name
            FROM activities a
            JOIN activity_types t ON a.activity_type_id = t.id
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Activity activity = new Activity();
                activity.setId(rs.getInt("id"));
                activity.setUserId(rs.getInt("user_id"));
                activity.setName(rs.getString("name"));
                activity.setDuration(rs.getInt("duration"));
                activity.setActivityDate(rs.getDate("activity_date").toLocalDate());

                ActivityType type = new ActivityType();
                type.setId(rs.getInt("activity_type_id"));
                type.setName(rs.getString("type_name"));

                activity.setType(type);
                activities.add(activity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activities;
    }
}