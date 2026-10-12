package com.Hospital.Management.backend.service;

import com.Hospital.Management.backend.dto.DashboardStats;
import com.Hospital.Management.backend.repository.AppointmentRepository;
import com.Hospital.Management.backend.repository.DoctorRepository;
import com.Hospital.Management.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public AdminService(DoctorRepository doctorRepository,
                        PatientRepository patientRepository,
                        AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public DashboardStats getDashboardStats() {
        long totalDoctors = doctorRepository.count();
        long totalPatients = patientRepository.count();
        long totalAppointments = appointmentRepository.count();
        long pendingAppointments = appointmentRepository.countByStatus("PENDING");
        long completedAppointments = appointmentRepository.countByStatus("COMPLETED");

        return new DashboardStats(totalDoctors, totalPatients, totalAppointments, pendingAppointments, completedAppointments);
    }
}
