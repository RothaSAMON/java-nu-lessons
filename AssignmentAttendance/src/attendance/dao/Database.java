package attendance.dao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {
    private static final String DB_DIRECTORY_NAME = "data";
    private static final String DB_FILE_NAME = "attendance.db";
    private static Connection sharedConnection;

    private Database() {}

    public static synchronized Connection getConnection() throws SQLException {
        if (sharedConnection == null || sharedConnection.isClosed()) {
            ensureDatabaseDirectory();
            String dbPath = getDatabasePath().toString();
            String url = "jdbc:sqlite:" + dbPath;
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                throw new SQLException("SQLite JDBC driver not found on classpath", e);
            }
            sharedConnection = DriverManager.getConnection(url);
            initializeSchema(sharedConnection);
        }
        return sharedConnection;
    }

    private static Path getDatabasePath() {
        String userDir = System.getProperty("user.dir");
        Path dir = Paths.get(userDir, DB_DIRECTORY_NAME);
        return dir.resolve(DB_FILE_NAME);
    }

    private static void ensureDatabaseDirectory() {
        try {
            Path dir = Paths.get(System.getProperty("user.dir"), DB_DIRECTORY_NAME);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create database directory", e);
        }
    }

    private static void initializeSchema(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("PRAGMA foreign_keys = ON");

            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "student_id TEXT UNIQUE NOT NULL, " +
                    "first_name TEXT NOT NULL, " +
                    "last_name TEXT NOT NULL, " +
                    "email TEXT, " +
                    "phone TEXT" +
                ")"
            );

            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "employee_id TEXT UNIQUE NOT NULL, " +
                    "name TEXT NOT NULL, " +
                    "role TEXT NOT NULL, " +
                    "email TEXT, " +
                    "phone TEXT" +
                ")"
            );

            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS classes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "code TEXT UNIQUE NOT NULL, " +
                    "name TEXT NOT NULL, " +
                    "description TEXT, " +
                    "schedule TEXT" +
                ")"
            );

            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS enrollments (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "class_id INTEGER NOT NULL, " +
                    "student_id INTEGER NOT NULL, " +
                    "UNIQUE(class_id, student_id), " +
                    "FOREIGN KEY(class_id) REFERENCES classes(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY(student_id) REFERENCES students(id) ON DELETE CASCADE" +
                ")"
            );

            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS attendance (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "class_id INTEGER NOT NULL, " +
                    "student_id INTEGER NOT NULL, " +
                    "date TEXT NOT NULL, " +
                    "status TEXT NOT NULL, " +
                    "note TEXT, " +
                    "UNIQUE(class_id, student_id, date), " +
                    "FOREIGN KEY(class_id) REFERENCES classes(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY(student_id) REFERENCES students(id) ON DELETE CASCADE" +
                ")"
            );
        }
    }
}

