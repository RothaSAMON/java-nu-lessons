package attendance.models;

import java.time.LocalDate;

public class AttendanceRecord {
    private Integer id;
    private Integer classId;
    private Integer studentId;
    private LocalDate date;
    private AttendanceStatus status;
    private String note;

    public AttendanceRecord() {}

    public AttendanceRecord(Integer id, Integer classId, Integer studentId, LocalDate date, AttendanceStatus status, String note) {
        this.id = id;
        this.classId = classId;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.note = note;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getClassId() { return classId; }
    public void setClassId(Integer classId) { this.classId = classId; }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}

