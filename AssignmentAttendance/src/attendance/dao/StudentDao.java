package attendance.dao;

import attendance.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public List<Student> listStudents(String searchText) {
        List<Student> results = new ArrayList<>();
        String baseSql = "SELECT id, student_id, first_name, last_name, email, phone FROM students";
        String where = (searchText == null || searchText.trim().isEmpty()) ? "" : " WHERE student_id LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
        String sql = baseSql + where + " ORDER BY last_name, first_name";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (!where.isEmpty()) {
                String pattern = "%" + searchText.trim() + "%";
                statement.setString(1, pattern);
                statement.setString(2, pattern);
                statement.setString(3, pattern);
                statement.setString(4, pattern);
            }
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list students", e);
        }
        return results;
    }

    public Student insertStudent(Student student) {
        String sql = "INSERT INTO students(student_id, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    student.setId(keys.getInt(1));
                }
            }
            return student;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert student", e);
        }
    }

    public void updateStudent(Student student) {
        if (student.getId() == null) throw new IllegalArgumentException("Student id is required for update");
        String sql = "UPDATE students SET student_id = ?, first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getStudentId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            statement.setInt(6, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update student", e);
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }

    public Student findById(int id) {
        String sql = "SELECT id, student_id, first_name, last_name, email, phone FROM students WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find student by id", e);
        }
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
            rs.getInt("id"),
            rs.getString("student_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("email"),
            rs.getString("phone")
        );
    }
}

