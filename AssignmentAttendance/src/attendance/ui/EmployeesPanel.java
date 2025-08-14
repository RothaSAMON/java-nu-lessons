package attendance.ui;

import attendance.dao.EmployeeDao;
import attendance.models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeesPanel extends JPanel {
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField searchField;
    private final JTextField empIdField;
    private final JTextField nameField;
    private final JTextField roleField;
    private final JTextField emailField;
    private final JTextField phoneField;

    public EmployeesPanel() {
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

        tableModel = new DefaultTableModel(new Object[]{"ID", "Employee ID", "Name", "Role", "Email", "Phone"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Employee ID"), gbc);
        gbc.gridx = 1; empIdField = new JTextField(15); form.add(empIdField, gbc);
        gbc.gridx = 0; gbc.gridy++; form.add(new JLabel("Name"), gbc);
        gbc.gridx = 1; nameField = new JTextField(15); form.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy++; form.add(new JLabel("Role"), gbc);
        gbc.gridx = 1; roleField = new JTextField(15); form.add(roleField, gbc);
        gbc.gridx = 0; gbc.gridy++; form.add(new JLabel("Email"), gbc);
        gbc.gridx = 1; emailField = new JTextField(15); form.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy++; form.add(new JLabel("Phone"), gbc);
        gbc.gridx = 1; phoneField = new JTextField(15); form.add(phoneField, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        buttons.add(addBtn); buttons.add(updateBtn); buttons.add(deleteBtn);
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
        List<Employee> employees = employeeDao.listEmployees(searchField.getText());
        for (Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getEmployeeId(), e.getName(), e.getRole(), e.getEmail(), e.getPhone()});
        }
    }

    private void onTableSelection() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        empIdField.setText(String.valueOf(tableModel.getValueAt(row, 1)));
        nameField.setText(String.valueOf(tableModel.getValueAt(row, 2)));
        roleField.setText(String.valueOf(tableModel.getValueAt(row, 3)));
        emailField.setText(String.valueOf(tableModel.getValueAt(row, 4)));
        phoneField.setText(String.valueOf(tableModel.getValueAt(row, 5)));
    }

    private void onAdd() {
        try {
            Employee e = new Employee(null, empIdField.getText().trim(), nameField.getText().trim(), roleField.getText().trim(), emailField.getText().trim(), phoneField.getText().trim());
            employeeDao.insertEmployee(e);
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
            Employee e = new Employee(dbId, empIdField.getText().trim(), nameField.getText().trim(), roleField.getText().trim(), emailField.getText().trim(), phoneField.getText().trim());
            employeeDao.updateEmployee(e);
            reloadTable();
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void onDelete() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        Integer dbId = (Integer) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Delete selected employee?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                employeeDao.deleteEmployee(dbId);
                reloadTable();
                clearForm();
            } catch (Exception ex) {
                showError(ex);
            }
        }
    }

    private void clearForm() {
        empIdField.setText("");
        nameField.setText("");
        roleField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

