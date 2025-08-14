package attendance.dao;

import attendance.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDao {
    public void enrollStudent(int classId, int studentId) {
        String sql = "INSERT OR IGNORE INTO enrollments(class_id, student_id) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to enroll student", e);
        }
    }

    public void removeEnrollment(int classId, int studentId) {
        String sql = "DELETE FROM enrollments WHERE class_id = ? AND student_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to remove enrollment", e);
        }
    }

    public List<Student> listStudentsForClass(int classId) {
        List<Student> results = new ArrayList<>();
        String sql = "SELECT s.id, s.student_id, s.first_name, s.last_name, s.email, s.phone " +
            "FROM enrollments e JOIN students s ON s.id = e.student_id WHERE e.class_id = ? ORDER BY s.last_name, s.first_name";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    results.add(new Student(
                        rs.getInt("id"),
                        rs.getString("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list students for class", e);
        }
        return results;
    }
}

