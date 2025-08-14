// --- FILE: EmployeeFormDialog.java ---
package com.attendance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeFormDialog extends JDialog {
    private JTextField nameField = new JTextField(25);
    private JTextField roleField = new JTextField(20);
    private JTextField deptField = new JTextField(20);
    private JTextField emailField = new JTextField(25);
    private JTextField phoneField = new JTextField(15);
    private boolean saved = false;
    private Employee employee;

    public EmployeeFormDialog(Window owner) {
        super(owner, "Employee Form", ModalityType.APPLICATION_MODAL);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.WEST;

        int y = 0;
        c.gridx = 0; c.gridy = y; panel.add(new JLabel("Name:"), c);
        c.gridx = 1; panel.add(nameField, c); y++;

        c.gridx = 0; c.gridy = y; panel.add(new JLabel("Role:"), c);
        c.gridx = 1; panel.add(roleField, c); y++;

        c.gridx = 0; c.gridy = y; panel.add(new JLabel("Department:"), c);
        c.gridx = 1; panel.add(deptField, c); y++;

        c.gridx = 0; c.gridy = y; panel.add(new JLabel("Email:"), c);
        c.gridx = 1; panel.add(emailField, c); y++;

        c.gridx = 0; c.gridy = y; panel.add(new JLabel("Phone:"), c);
        c.gridx = 1; panel.add(phoneField, c); y++;

        JPanel buttons = new JPanel();
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().trim().isEmpty() || roleField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(EmployeeFormDialog.this, "Name and Role are required.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                saved = true;
                EmployeeFormDialog.this.setVisible(false);
            }
        });

        cancelBtn.addActionListener(ev -> {
            saved = false;
            EmployeeFormDialog.this.setVisible(false);
        });

        buttons.add(saveBtn);
        buttons.add(cancelBtn);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void loadEmployee(Employee e) {
        this.employee = e;
        if (e != null) {
            nameField.setText(e.getName());
            roleField.setText(e.getRole());
            deptField.setText(e.getDepartment());
            emailField.setText(e.getEmail());
            phoneField.setText(e.getPhone());
        }
    }

    public Employee getEmployeeFromForm() {
        if (employee == null) employee = new Employee();
        employee.setName(nameField.getText().trim());
        employee.setRole(roleField.getText().trim());
        employee.setDepartment(deptField.getText().trim());
        employee.setEmail(emailField.getText().trim());
        employee.setPhone(phoneField.getText().trim());
        return employee;
    }

    public boolean isSaved() { return saved; }
}
