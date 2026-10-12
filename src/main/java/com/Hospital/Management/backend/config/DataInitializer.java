package com.Hospital.Management.backend.config;

import com.Hospital.Management.backend.entity.Appointment;
import com.Hospital.Management.backend.entity.Doctor;
import com.Hospital.Management.backend.entity.Patient;
import com.Hospital.Management.backend.entity.User;
import com.Hospital.Management.backend.repository.AppointmentRepository;
import com.Hospital.Management.backend.repository.DoctorRepository;
import com.Hospital.Management.backend.repository.PatientRepository;
import com.Hospital.Management.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           DoctorRepository doctorRepository,
                           PatientRepository patientRepository,
                           AppointmentRepository appointmentRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        String defaultPassword = passwordEncoder.encode("password123");

        // Seed Users
        userRepository.save(new User("System Admin", "admin@hospital.com", defaultPassword, "ADMIN"));
        userRepository.save(new User("Dr. Robert Ford", "ford@hospital.com", defaultPassword, "DOCTOR"));
        userRepository.save(new User("Dr. Meredith Grey", "grey@hospital.com", defaultPassword, "DOCTOR"));
        userRepository.save(new User("Dr. Gregory House", "house@hospital.com", defaultPassword, "DOCTOR"));
        userRepository.save(new User("Alice Smith", "alice@gmail.com", defaultPassword, "PATIENT"));
        userRepository.save(new User("John Watson", "john.watson@gmail.com", defaultPassword, "PATIENT"));
        userRepository.save(new User("Clara Oswald", "clara@gmail.com", defaultPassword, "PATIENT"));

        // Seed Doctors
        Doctor d1 = doctorRepository.save(new Doctor("Dr. Robert Ford", "Cardiology", "ford@hospital.com", "+1 555-0101"));
        Doctor d2 = doctorRepository.save(new Doctor("Dr. Meredith Grey", "General Surgery", "grey@hospital.com", "+1 555-0102"));
        Doctor d3 = doctorRepository.save(new Doctor("Dr. Gregory House", "Diagnostic Medicine", "house@hospital.com", "+1 555-0103"));

        // Seed Patients
        Patient p1 = patientRepository.save(new Patient("Alice Smith", 29, "Female", "alice@gmail.com", "+1 555-0201"));
        Patient p2 = patientRepository.save(new Patient("John Watson", 42, "Male", "john.watson@gmail.com", "+1 555-0202"));
        Patient p3 = patientRepository.save(new Patient("Clara Oswald", 31, "Female", "clara@gmail.com", "+1 555-0203"));

        // Seed Appointments
        appointmentRepository.save(new Appointment(d1.getId(), p1.getId(), "2026-10-10", "10:00 AM", "CONFIRMED"));
        appointmentRepository.save(new Appointment(d2.getId(), p2.getId(), "2026-10-11", "02:30 PM", "PENDING"));
        appointmentRepository.save(new Appointment(d3.getId(), p3.getId(), "2026-10-12", "11:15 AM", "COMPLETED"));
    }
}
