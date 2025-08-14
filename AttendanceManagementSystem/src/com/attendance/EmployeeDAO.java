// --- FILE: EmployeeDAO.java ---
package com.attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee e) throws SQLException {
        String sql = "INSERT INTO employees (name, role, department, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getRole());
            ps.setString(3, e.getDepartment());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPhone());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) e.setId(rs.getInt(1));
            }
        }
    }

    public void updateEmployee(Employee e) throws SQLException {
        String sql = "UPDATE employees SET name=?, role=?, department=?, email=?, phone=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getRole());
            ps.setString(3, e.getDepartment());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPhone());
            ps.setInt(6, e.getId());
            ps.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT id, name, role, department, email, phone FROM employees ORDER BY id DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employee e = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getString("department"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                list.add(e);
            }
        }
        return list;
    }

    public List<Employee> searchEmployees(String keyword) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT id, name, role, department, email, phone FROM employees WHERE name LIKE ? OR role LIKE ? OR department LIKE ? OR email LIKE ? ORDER BY id DESC";
        String k = "%" + keyword + "%";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k);
            ps.setString(2, k);
            ps.setString(3, k);
            ps.setString(4, k);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee e = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone")
                    );
                    list.add(e);
                }
            }
        }
        return list;
    }
}
