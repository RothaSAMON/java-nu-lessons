package attendance.models;

public class CourseClass {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private String schedule; // e.g., Mon/Wed 10:00-11:00

    public CourseClass() {}

    public CourseClass(Integer id, String code, String name, String description, String schedule) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}

