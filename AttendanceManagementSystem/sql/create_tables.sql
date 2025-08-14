-- Create database
CREATE DATABASE attendance_db;
USE attendance_db;

-- Create employees table
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    role VARCHAR(80) NOT NULL,
    department VARCHAR(100),
    email VARCHAR(120),
    phone VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
