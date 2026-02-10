package com.company.repositories;

import com.company.models.ActivityType;
import com.company.repositories.interfaces.IActivityTypeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityTypeRepository implements IActivityTypeRepository {

    private final Connection connection;

    public ActivityTypeRepository(Connection connection) {
        this.connection = connection;
    }

    public List<ActivityType> getAll() {
        List<ActivityType> types = new ArrayList<>();
        String sql = "SELECT id, name FROM activity_types";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                types.add(new ActivityType(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public ActivityType getById(int id) {
        String sql = "SELECT id, name FROM activity_types WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ActivityType(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(ActivityType type) {
        String sql = "INSERT INTO activity_types(name) VALUES (?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, type.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ActivityType> getByCategoryId(int categoryId) {
        List<ActivityType> types = new ArrayList<>();

        String sql = """
            SELECT at.id, at.name
            FROM activity_types at
            WHERE at.category_id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                types.add(new ActivityType(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
}