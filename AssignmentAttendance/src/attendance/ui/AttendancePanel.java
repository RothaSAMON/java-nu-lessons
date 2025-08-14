package attendance.ui;

import attendance.dao.AttendanceDao;
import attendance.dao.ClassDao;
import attendance.dao.EnrollmentDao;
import attendance.models.AttendanceRecord;
import attendance.models.AttendanceStatus;
import attendance.models.CourseClass;
import attendance.models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendancePanel extends JPanel {
    private final ClassDao classDao = new ClassDao();
    private final EnrollmentDao enrollmentDao = new EnrollmentDao();
    private final AttendanceDao attendanceDao = new AttendanceDao();

    private final JComboBox<ClassWrapper> classCombo;
    private final JSpinner dateSpinner;
    private final DefaultTableModel tableModel;
    private final JTable table;

    public AttendancePanel() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        classCombo = new JComboBox<>();
        top.add(new JLabel("Class:"));
        top.add(classCombo);
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        top.add(new JLabel("Date:"));
        top.add(dateSpinner);
        JButton loadBtn = new JButton("Load");
        top.add(loadBtn);
        JButton saveBtn = new JButton("Save");
        top.add(saveBtn);
        add(top, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Student ID", "Name", "Status", "Note"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return c >= 2; }
        };
        table = new JTable(tableModel);
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{
                AttendanceStatus.PRESENT.name(),
                AttendanceStatus.ABSENT.name(),
                AttendanceStatus.EXCUSED.name(),
                AttendanceStatus.PERMISSION.name()
        });
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(statusCombo));
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBtn.addActionListener(e -> loadAttendance());
        saveBtn.addActionListener(e -> saveAttendance());

        reloadClassCombo();
    }

    private void reloadClassCombo() {
        classCombo.removeAllItems();
        List<CourseClass> classes = classDao.listClasses(null);
        for (CourseClass c : classes) classCombo.addItem(new ClassWrapper(c));
    }

    private LocalDate getSelectedDate() {
        Object value = dateSpinner.getValue();
        java.util.Date utilDate = (java.util.Date) value;
        return LocalDate.of(utilDate.getYear() + 1900, utilDate.getMonth() + 1, utilDate.getDate());
    }

    private void loadAttendance() {
        ClassWrapper wrapper = (ClassWrapper) classCombo.getSelectedItem();
        if (wrapper == null) return;
        LocalDate date = getSelectedDate();
        tableModel.setRowCount(0);
        List<Student> students = enrollmentDao.listStudentsForClass(wrapper.courseClass.getId());
        var existing = attendanceDao.getAttendanceForClassAndDate(wrapper.courseClass.getId(), date);
        Map<Integer, AttendanceRecord> byStudentId = new HashMap<>();
        for (AttendanceRecord r : existing) byStudentId.put(r.getStudentId(), r);
        for (Student s : students) {
            AttendanceRecord r = byStudentId.get(s.getId());
            String status = r != null ? r.getStatus().name() : AttendanceStatus.PRESENT.name();
            String note = r != null ? r.getNote() : "";
            tableModel.addRow(new Object[]{s.getStudentId(), s.getFullName(), status, note});
        }
    }

    private void saveAttendance() {
        ClassWrapper wrapper = (ClassWrapper) classCombo.getSelectedItem();
        if (wrapper == null) return;
        LocalDate date = getSelectedDate();
        List<Student> students = enrollmentDao.listStudentsForClass(wrapper.courseClass.getId());
        Map<String, Integer> studentCodeToId = new HashMap<>();
        for (Student s : students) studentCodeToId.put(s.getStudentId(), s.getId());
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String studentCode = String.valueOf(tableModel.getValueAt(i, 0));
            String statusStr = String.valueOf(tableModel.getValueAt(i, 2));
            String note = String.valueOf(tableModel.getValueAt(i, 3));
            Integer studentId = studentCodeToId.get(studentCode);
            if (studentId == null) continue;
            AttendanceRecord record = new AttendanceRecord(null, wrapper.courseClass.getId(), studentId, date, AttendanceStatus.fromString(statusStr), note);
            attendanceDao.upsertAttendance(record);
        }
        JOptionPane.showMessageDialog(this, "Attendance saved.");
    }

    private static class ClassWrapper {
        private final CourseClass courseClass;
        private ClassWrapper(CourseClass c) { this.courseClass = c; }
        @Override public String toString() { return courseClass.getCode() + " - " + courseClass.getName(); }
    }
}

