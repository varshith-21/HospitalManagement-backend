package com.Hospital.Management.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Hospital.Management.backend.entity.Appointment;
import com.Hospital.Management.backend.repository.AppointmentRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment updateAppointment(Long id, Appointment appointment) {

        Appointment existing = getAppointmentById(id);

        existing.setDoctorId(appointment.getDoctorId());
        existing.setPatientId(appointment.getPatientId());
        existing.setAppointmentDate(appointment.getAppointmentDate());
        existing.setAppointmentTime(appointment.getAppointmentTime());
        existing.setStatus(appointment.getStatus());

        return appointmentRepository.save(existing);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}