Attendance Management System (Java Swing + MySQL)

Requirements:
- JDK 11+
- MySQL server
- MySQL Connector/J (mysql-connector-java)

Database setup (run in MySQL):

CREATE DATABASE attendance_db;
USE attendance_db;

CREATE TABLE employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  role VARCHAR(80) NOT NULL,
  department VARCHAR(100),
  email VARCHAR(120),
  phone VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


How to build and run
--------------------
1. Add mysql-connector-java jar to classpath (or add dependency in Maven/Gradle).
   Maven dependency example:

<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.33</version>
</dependency>

2. Update DBConnection.java constants for DB_URL, DB_USER, DB_PASS.
3. Compile all .java files and run MainFrame (contains main method).
