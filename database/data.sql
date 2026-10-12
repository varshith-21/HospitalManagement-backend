-- Sample Data for Hospital Management System
USE HospitalManagement;

-- Seed Users (Password is BCrypt encrypted 'password123' or plain fallback)
INSERT INTO users (name, email, password, role) VALUES
('System Admin', 'admin@hospital.com', '$2a$10$w8.yD0bB5jK1zZ3S6X.8y.nJ/2M6Z3S6X.8y.nJ/2M6Z3S6X.8y', 'ADMIN'),
('Dr. Robert Ford', 'ford@hospital.com', '$2a$10$w8.yD0bB5jK1zZ3S6X.8y.nJ/2M6Z3S6X.8y.nJ/2M6Z3S6X.8y', 'DOCTOR'),
('Alice Smith', 'alice@gmail.com', '$2a$10$w8.yD0bB5jK1zZ3S6X.8y.nJ/2M6Z3S6X.8y.nJ/2M6Z3S6X.8y', 'PATIENT');

-- Seed Doctors
INSERT INTO doctors (name, specialization, email, phone) VALUES
('Dr. Robert Ford', 'Cardiology', 'ford@hospital.com', '+1 555-0101'),
('Dr. Meredith Grey', 'General Surgery', 'grey@hospital.com', '+1 555-0102'),
('Dr. Gregory House', 'Diagnostic Medicine', 'house@hospital.com', '+1 555-0103');

-- Seed Patients
INSERT INTO patients (name, age, gender, email, phone) VALUES
('Alice Smith', 29, 'Female', 'alice@gmail.com', '+1 555-0201'),
('John Watson', 42, 'Male', 'john.watson@gmail.com', '+1 555-0202'),
('Clara Oswald', 31, 'Female', 'clara@gmail.com', '+1 555-0203');

-- Seed Appointments
INSERT INTO appointments (doctor_id, patient_id, appointment_date, appointment_time, status) VALUES
(1, 1, '2026-10-10', '10:00 AM', 'CONFIRMED'),
(2, 2, '2026-10-11', '02:30 PM', 'PENDING'),
(3, 3, '2026-10-12', '11:15 AM', 'COMPLETED');
