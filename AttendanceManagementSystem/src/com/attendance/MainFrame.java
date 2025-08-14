// --- FILE: MainFrame.java ---
package com.attendance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    private EmployeeDAO dao = new EmployeeDAO();
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField = new JTextField(20);

    public MainFrame() {
        super("Attendance Management System - Employee Management");
        initComponents();
        loadTable();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new Object[]{"ID", "Name", "Role", "Department", "Email", "Phone"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(table);

        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton delBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");

        addBtn.addActionListener(e -> onAdd());
        editBtn.addActionListener(e -> onEdit());
        delBtn.addActionListener(e -> onDelete());
        refreshBtn.addActionListener(e -> loadTable());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Search:"));
        top.add(searchField);
        JButton searchBtn = new JButton("Go");
        top.add(searchBtn);
        searchBtn.addActionListener(ev -> onSearch());

        JPanel buttons = new JPanel();
        buttons.add(addBtn);
        buttons.add(editBtn);
        buttons.add(delBtn);
        buttons.add(refreshBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);

        // double-click to edit
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) onEdit();
            }
        });

        // keyboard delete
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) onDelete();
            }
        });
    }

    private void loadTable() {
        try {
            model.setRowCount(0);
            List<Employee> list = dao.getAllEmployees();
            for (Employee e : list) {
                model.addRow(new Object[]{e.getId(), e.getName(), e.getRole(), e.getDepartment(), e.getEmail(), e.getPhone()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void onSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) { loadTable(); return; }
        try {
            model.setRowCount(0);
            List<Employee> list = dao.searchEmployees(keyword);
            for (Employee e : list) {
                model.addRow(new Object[]{e.getId(), e.getName(), e.getRole(), e.getDepartment(), e.getEmail(), e.getPhone()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Search failed: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void onAdd() {
        EmployeeFormDialog dlg = new EmployeeFormDialog(this);
        dlg.setVisible(true);
        if (dlg.isSaved()) {
            Employee e = dlg.getEmployeeFromForm();
            try {
                dao.addEmployee(e);
                loadTable();
                JOptionPane.showMessageDialog(this, "Employee added.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Failed to add: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private void onEdit() {
        int sel = table.getSelectedRow();
        if (sel == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to edit.");
            return;
        }
        int id = (int) model.getValueAt(sel, 0);
        Employee e = new Employee();
        e.setId(id);
        e.setName((String) model.getValueAt(sel, 1));
        e.setRole((String) model.getValueAt(sel, 2));
        e.setDepartment((String) model.getValueAt(sel, 3));
        e.setEmail((String) model.getValueAt(sel, 4));
        e.setPhone((String) model.getValueAt(sel, 5));

        EmployeeFormDialog dlg = new EmployeeFormDialog(this);
        dlg.loadEmployee(e);
        dlg.setVisible(true);
        if (dlg.isSaved()) {
            Employee updated = dlg.getEmployeeFromForm();
            try {
                dao.updateEmployee(updated);
                loadTable();
                JOptionPane.showMessageDialog(this, "Employee updated.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Failed to update: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private void onDelete() {
        int sel = table.getSelectedRow();
        if (sel == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }
        int id = (int) model.getValueAt(sel, 0);
        int ok = JOptionPane.showConfirmDialog(this, "Delete selected employee?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION) return;
        try {
            dao.deleteEmployee(id);
            loadTable();
            JOptionPane.showMessageDialog(this, "Employee deleted.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to delete: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new MainFrame().setVisible(true);
        });
    }
}
