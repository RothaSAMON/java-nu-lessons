package attendance.dao;

import attendance.models.AttendanceRecord;
import attendance.models.AttendanceStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
    public void upsertAttendance(AttendanceRecord record) {
        String sql = "INSERT INTO attendance(class_id, student_id, date, status, note) VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT(class_id, student_id, date) DO UPDATE SET status = excluded.status, note = excluded.note";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, record.getClassId());
            statement.setInt(2, record.getStudentId());
            statement.setString(3, record.getDate().toString());
            statement.setString(4, record.getStatus().name());
            statement.setString(5, record.getNote());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to upsert attendance", e);
        }
    }

    public List<AttendanceRecord> getAttendanceForClassAndDate(int classId, LocalDate date) {
        List<AttendanceRecord> results = new ArrayList<>();
        String sql = "SELECT id, class_id, student_id, date, status, note FROM attendance WHERE class_id = ? AND date = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, classId);
            statement.setString(2, date.toString());
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) results.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch attendance records", e);
        }
        return results;
    }

    private AttendanceRecord mapRow(ResultSet rs) throws SQLException {
        return new AttendanceRecord(
            rs.getInt("id"),
            rs.getInt("class_id"),
            rs.getInt("student_id"),
            LocalDate.parse(rs.getString("date")),
            AttendanceStatus.fromString(rs.getString("status")),
            rs.getString("note")
        );
    }
}

