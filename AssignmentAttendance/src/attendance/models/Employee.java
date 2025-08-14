package attendance.models;

public class Employee {
    private Integer id;
    private String employeeId;
    private String name;
    private String role;
    private String email;
    private String phone;

    public Employee() {}

    public Employee(Integer id, String employeeId, String name, String role, String email, String phone) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

