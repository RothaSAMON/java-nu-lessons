package attendance.dao;

import attendance.models.CourseClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDao {
    public List<CourseClass> listClasses(String searchText) {
        List<CourseClass> results = new ArrayList<>();
        String baseSql = "SELECT id, code, name, description, schedule FROM classes";
        String where = (searchText == null || searchText.trim().isEmpty()) ? "" : " WHERE code LIKE ? OR name LIKE ?";
        String sql = baseSql + where + " ORDER BY code";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (!where.isEmpty()) {
                String pattern = "%" + searchText.trim() + "%";
                statement.setString(1, pattern);
                statement.setString(2, pattern);
            }
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) results.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list classes", e);
        }
        return results;
    }

    public CourseClass insertClass(CourseClass courseClass) {
        String sql = "INSERT INTO classes(code, name, description, schedule) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, courseClass.getCode());
            statement.setString(2, courseClass.getName());
            statement.setString(3, courseClass.getDescription());
            statement.setString(4, courseClass.getSchedule());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) courseClass.setId(keys.getInt(1));
            }
            return courseClass;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert class", e);
        }
    }

    public void updateClass(CourseClass courseClass) {
        if (courseClass.getId() == null) throw new IllegalArgumentException("Class id is required for update");
        String sql = "UPDATE classes SET code = ?, name = ?, description = ?, schedule = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseClass.getCode());
            statement.setString(2, courseClass.getName());
            statement.setString(3, courseClass.getDescription());
            statement.setString(4, courseClass.getSchedule());
            statement.setInt(5, courseClass.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update class", e);
        }
    }

    public void deleteClass(int id) {
        String sql = "DELETE FROM classes WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete class", e);
        }
    }

    public CourseClass findById(int id) {
        String sql = "SELECT id, code, name, description, schedule FROM classes WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find class by id", e);
        }
    }

    private CourseClass mapRow(ResultSet rs) throws SQLException {
        return new CourseClass(
            rs.getInt("id"),
            rs.getString("code"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("schedule")
        );
    }
}

