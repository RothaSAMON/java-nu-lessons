// --- FILE: Employee.java ---
package com.attendance;

public class Employee {
    private int id;
    private String name;
    private String role;
    private String department;
    private String email;
    private String phone;

    public Employee() {}

    public Employee(int id, String name, String role, String department, String email, String phone) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    public Employee(String name, String role, String department, String email, String phone) {
        this(0, name, role, department, email, phone);
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}