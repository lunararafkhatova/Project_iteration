package com.company.repositories;

import com.company.models.Activity;
import com.company.models.ActivityType;
import com.company.models.ActivityFullDTO;
import com.company.repositories.interfaces.IActivityRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository implements IActivityRepository {

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

                Date d = rs.getDate("activity_date");
                if (d != null) {
                    activity.setActivityDate(d.toLocalDate());
                }

                ActivityType type = new ActivityType();
                type.setId(rs.getInt("activity_type_id"));
                type.setName(rs.getString("type_name"));

                activity.setType(type);
                activities.add(activity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return activities;
    }

    public boolean create(Activity activity) {
        String sql = "INSERT INTO activities (user_id, activity_type_id, name, duration, activity_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, activity.getUserId());
            ps.setInt(2, activity.getType().getId());
            ps.setString(3, activity.getName());
            ps.setInt(4, activity.getDuration());
            ps.setDate(5, Date.valueOf(activity.getActivityDate()));

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM activities WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<ActivityFullDTO> getFullActivities() {
        List<ActivityFullDTO> list = new ArrayList<>();

        String sql = """
            SELECT a.id,
                   u.name,
                   a.activity_date,
                   at.name,
                   c.name,
                   a.duration
            FROM activities a
            JOIN users u ON u.id = a.user_id
            JOIN activity_types at ON a.activity_type_id = at.id
            JOIN activity_categories c ON at.category_id = c.id
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new ActivityFullDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<String> getActivitiesByCategory(int categoryId) {

        List<String> list = new ArrayList<>();

        String sql = """
            SELECT a.name
            FROM activities a
            JOIN activity_types t ON a.activity_type_id = t.id
            WHERE t.category_id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    public ActivityFullDTO getActivityDetails(int activityId) {
        String sql = """
        SELECT a.id, u.name, a.activity_date, at.name, c.name, a.duration
        FROM activities a
        JOIN users u ON u.id = a.user_id
        JOIN activity_types at ON a.activity_type_id = at.id
        JOIN activity_categories c ON at.category_id = c.id
        WHERE a.id = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, activityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ActivityFullDTO(
                            rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(),
                            rs.getString(4), rs.getString(5), rs.getInt(6)
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching activity details: " + e.getMessage());
        }
        return null;
    }
}
