package attendance.ui;

import attendance.dao.ClassDao;
import attendance.dao.EnrollmentDao;
import attendance.dao.StudentDao;
import attendance.models.CourseClass;
import attendance.models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClassesPanel extends JPanel {
    private final ClassDao classDao = new ClassDao();
    private final StudentDao studentDao = new StudentDao();
    private final EnrollmentDao enrollmentDao = new EnrollmentDao();

    private final DefaultTableModel classTableModel;
    private final JTable classTable;
    private final JTextField searchField;
    private final JTextField codeField;
    private final JTextField nameField;
    private final JTextField scheduleField;
    private final JTextArea descriptionArea;

    private final DefaultTableModel enrolledTableModel;
    private final JTable enrolledTable;
    private final JComboBox<StudentWrapper> studentCombo;

    public ClassesPanel() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> reloadClassTable());
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        top.add(searchPanel, BorderLayout.WEST);
        add(top, BorderLayout.NORTH);

        classTableModel = new DefaultTableModel(new Object[]{"ID", "Code", "Name", "Schedule"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        classTable = new JTable(classTableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(classTable), BorderLayout.CENTER);

        // Right details + enrollment panel
        JPanel right = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Code"), gbc); gbc.gridx = 1; codeField = new JTextField(15); form.add(codeField, gbc);
        gbc.gridx = 0; gbc.gridy++; form.add(new JLabel("Name"), gbc); gbc.gridx = 1; nameField = new JTextField(15); form.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy++; form.add(new JLabel("Schedule"), gbc); gbc.gridx = 1; scheduleField = new JTextField(15); form.add(scheduleField, gbc);
        gbc.gridx = 0; gbc.gridy++; form.add(new JLabel("Description"), gbc); gbc.gridx = 1; descriptionArea = new JTextArea(4, 15); form.add(new JScrollPane(descriptionArea), gbc);

        JPanel formButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        formButtons.add(addBtn); formButtons.add(updateBtn); formButtons.add(deleteBtn);

        addBtn.addActionListener(e -> onAddClass());
        updateBtn.addActionListener(e -> onUpdateClass());
        deleteBtn.addActionListener(e -> onDeleteClass());

        JPanel enrollmentPanel = new JPanel(new BorderLayout());
        enrollmentPanel.setBorder(BorderFactory.createTitledBorder("Enrollments"));
        enrolledTableModel = new DefaultTableModel(new Object[]{"ID", "Student ID", "Name", "Email"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        enrolledTable = new JTable(enrolledTableModel);
        enrollmentPanel.add(new JScrollPane(enrolledTable), BorderLayout.CENTER);

        JPanel enrollmentControls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        studentCombo = new JComboBox<>();
        JButton enrollBtn = new JButton("Enroll");
        JButton removeBtn = new JButton("Remove");
        enrollmentControls.add(new JLabel("Student:"));
        enrollmentControls.add(studentCombo);
        enrollmentControls.add(enrollBtn);
        enrollmentControls.add(removeBtn);
        enrollmentPanel.add(enrollmentControls, BorderLayout.SOUTH);

        enrollBtn.addActionListener(e -> onEnroll());
        removeBtn.addActionListener(e -> onRemoveEnrollment());

        JPanel rightTop = new JPanel(new BorderLayout());
        rightTop.add(form, BorderLayout.CENTER);
        rightTop.add(formButtons, BorderLayout.SOUTH);
        right.add(rightTop, BorderLayout.NORTH);
        right.add(enrollmentPanel, BorderLayout.CENTER);

        add(right, BorderLayout.EAST);

        classTable.getSelectionModel().addListSelectionListener(e -> onClassSelection());

        reloadClassTable();
        reloadStudentCombo();
    }

    private void reloadClassTable() {
        classTableModel.setRowCount(0);
        List<CourseClass> classes = classDao.listClasses(searchField.getText());
        for (CourseClass c : classes) {
            classTableModel.addRow(new Object[]{c.getId(), c.getCode(), c.getName(), c.getSchedule()});
        }
    }

    private void reloadStudentCombo() {
        studentCombo.removeAllItems();
        List<Student> students = studentDao.listStudents(null);
        for (Student s : students) {
            studentCombo.addItem(new StudentWrapper(s));
        }
    }

    private void reloadEnrollments() {
        enrolledTableModel.setRowCount(0);
        int row = classTable.getSelectedRow();
        if (row < 0) return;
        Integer classId = (Integer) classTableModel.getValueAt(row, 0);
        List<Student> students = enrollmentDao.listStudentsForClass(classId);
        for (Student s : students) {
            enrolledTableModel.addRow(new Object[]{s.getId(), s.getStudentId(), s.getFullName(), s.getEmail()});
        }
    }

    private void onClassSelection() {
        int row = classTable.getSelectedRow();
        if (row < 0) return;
        // For simplicity, fill form with table fields; description requires fetch
        codeField.setText(String.valueOf(classTableModel.getValueAt(row, 1)));
        nameField.setText(String.valueOf(classTableModel.getValueAt(row, 2)));
        scheduleField.setText(String.valueOf(classTableModel.getValueAt(row, 3)));
        Integer classId = (Integer) classTableModel.getValueAt(row, 0);
        var full = classDao.findById(classId);
        if (full != null) descriptionArea.setText(full.getDescription());
        reloadEnrollments();
    }

    private void onAddClass() {
        try {
            CourseClass c = new CourseClass(null, codeField.getText().trim(), nameField.getText().trim(), descriptionArea.getText().trim(), scheduleField.getText().trim());
            classDao.insertClass(c);
            reloadClassTable();
            clearClassForm();
        } catch (Exception ex) { showError(ex); }
    }

    private void onUpdateClass() {
        int row = classTable.getSelectedRow();
        if (row < 0) return;
        try {
            Integer id = (Integer) classTableModel.getValueAt(row, 0);
            CourseClass c = new CourseClass(id, codeField.getText().trim(), nameField.getText().trim(), descriptionArea.getText().trim(), scheduleField.getText().trim());
            classDao.updateClass(c);
            reloadClassTable();
        } catch (Exception ex) { showError(ex); }
    }

    private void onDeleteClass() {
        int row = classTable.getSelectedRow();
        if (row < 0) return;
        Integer id = (Integer) classTableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Delete selected class?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try { classDao.deleteClass(id); reloadClassTable(); clearClassForm(); enrolledTableModel.setRowCount(0); }
            catch (Exception ex) { showError(ex); }
        }
    }

    private void onEnroll() {
        int row = classTable.getSelectedRow();
        if (row < 0) return;
        StudentWrapper wrapper = (StudentWrapper) studentCombo.getSelectedItem();
        if (wrapper == null) return;
        Integer classId = (Integer) classTableModel.getValueAt(row, 0);
        try { enrollmentDao.enrollStudent(classId, wrapper.student.getId()); reloadEnrollments(); }
        catch (Exception ex) { showError(ex); }
    }

    private void onRemoveEnrollment() {
        int cRow = classTable.getSelectedRow();
        int eRow = enrolledTable.getSelectedRow();
        if (cRow < 0 || eRow < 0) return;
        Integer classId = (Integer) classTableModel.getValueAt(cRow, 0);
        Integer studentId = (Integer) enrolledTableModel.getValueAt(eRow, 0);
        try { enrollmentDao.removeEnrollment(classId, studentId); reloadEnrollments(); }
        catch (Exception ex) { showError(ex); }
    }

    private void clearClassForm() {
        codeField.setText("");
        nameField.setText("");
        scheduleField.setText("");
        descriptionArea.setText("");
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static class StudentWrapper {
        private final Student student;
        private StudentWrapper(Student student) { this.student = student; }
        @Override public String toString() { return student.getStudentId() + " - " + student.getFullName(); }
    }
}

