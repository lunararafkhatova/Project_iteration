package com.company.repositories;

import com.company.models.ActivityCategory;
import com.company.repositories.interfaces.IActivityCategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityCategoryRepository implements IActivityCategoryRepository {

    private final Connection connection;

    public ActivityCategoryRepository(Connection connection) {
        this.connection = connection;
    }

    public List<ActivityCategory> getAll() {
        List<ActivityCategory> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM activity_categories";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categories.add(new ActivityCategory(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}