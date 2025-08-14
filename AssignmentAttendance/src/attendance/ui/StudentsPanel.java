package attendance.ui;

import attendance.dao.StudentDao;
import attendance.models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentsPanel extends JPanel {
    private final StudentDao studentDao = new StudentDao();
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField searchField;
    private final JTextField idField;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField emailField;
    private final JTextField phoneField;

    public StudentsPanel() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> reloadTable());
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        top.add(searchPanel, BorderLayout.WEST);

        add(top, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Student ID", "First Name", "Last Name", "Email", "Phone"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Student ID"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        form.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("First Name"), gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(15);
        form.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Last Name"), gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(15);
        form.add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Email"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        form.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        form.add(new JLabel("Phone"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(15);
        form.add(phoneField, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);

        addBtn.addActionListener(e -> onAdd());
        updateBtn.addActionListener(e -> onUpdate());
        deleteBtn.addActionListener(e -> onDelete());

        JPanel south = new JPanel(new BorderLayout());
        south.add(form, BorderLayout.CENTER);
        south.add(buttons, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e -> onTableSelection());

        reloadTable();
    }

    private void reloadTable() {
        tableModel.setRowCount(0);
        List<Student> students = studentDao.listStudents(searchField.getText());
        for (Student s : students) {
            tableModel.addRow(new Object[]{s.getId(), s.getStudentId(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getPhone()});
        }
    }

    private void onTableSelection() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        idField.setText(String.valueOf(tableModel.getValueAt(row, 1)));
        firstNameField.setText(String.valueOf(tableModel.getValueAt(row, 2)));
        lastNameField.setText(String.valueOf(tableModel.getValueAt(row, 3)));
        emailField.setText(String.valueOf(tableModel.getValueAt(row, 4)));
        phoneField.setText(String.valueOf(tableModel.getValueAt(row, 5)));
    }

    private void onAdd() {
        try {
            Student s = new Student(null, idField.getText().trim(), firstNameField.getText().trim(), lastNameField.getText().trim(), emailField.getText().trim(), phoneField.getText().trim());
            studentDao.insertStudent(s);
            reloadTable();
            clearForm();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void onUpdate() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        try {
            Integer dbId = (Integer) tableModel.getValueAt(row, 0);
            Student s = new Student(dbId, idField.getText().trim(), firstNameField.getText().trim(), lastNameField.getText().trim(), emailField.getText().trim(), phoneField.getText().trim());
            studentDao.updateStudent(s);
            reloadTable();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void onDelete() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        Integer dbId = (Integer) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Delete selected student?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                studentDao.deleteStudent(dbId);
                reloadTable();
                clearForm();
            } catch (Exception ex) {
                showError(ex);
            }
        }
    }

    private void clearForm() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

