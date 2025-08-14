package attendance.models;

public enum AttendanceStatus {
    PRESENT,
    ABSENT,
    EXCUSED,
    PERMISSION;

    public static AttendanceStatus fromString(String value) {
        if (value == null) return PRESENT;
        switch (value.toUpperCase()) {
            case "ABSENT": return ABSENT;
            case "EXCUSED": return EXCUSED;
            case "PERMISSION": return PERMISSION;
            default: return PRESENT;
        }
    }
}

