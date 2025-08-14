package attendance.dao;

import attendance.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    public List<Employee> listEmployees(String searchText) {
        List<Employee> results = new ArrayList<>();
        String baseSql = "SELECT id, employee_id, name, role, email, phone FROM employees";
        String where = (searchText == null || searchText.trim().isEmpty()) ? "" : " WHERE employee_id LIKE ? OR name LIKE ? OR role LIKE ? OR email LIKE ?";
        String sql = baseSql + where + " ORDER BY name";
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
            throw new RuntimeException("Failed to list employees", e);
        }
        return results;
    }

    public Employee insertEmployee(Employee employee) {
        String sql = "INSERT INTO employees(employee_id, name, role, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getEmployeeId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getRole());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPhone());
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    employee.setId(keys.getInt(1));
                }
            }
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert employee", e);
        }
    }

    public void updateEmployee(Employee employee) {
        if (employee.getId() == null) throw new IllegalArgumentException("Employee id is required for update");
        String sql = "UPDATE employees SET employee_id = ?, name = ?, role = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getEmployeeId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getRole());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPhone());
            statement.setInt(6, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update employee", e);
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete employee", e);
        }
    }

    public Employee findById(int id) {
        String sql = "SELECT id, employee_id, name, role, email, phone FROM employees WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) return mapRow(rs);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find employee by id", e);
        }
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
            rs.getInt("id"),
            rs.getString("employee_id"),
            rs.getString("name"),
            rs.getString("role"),
            rs.getString("email"),
            rs.getString("phone")
        );
    }
}

