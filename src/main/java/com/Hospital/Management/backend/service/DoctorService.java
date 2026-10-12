package com.Hospital.Management.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Hospital.Management.backend.entity.Doctor;
import com.Hospital.Management.backend.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {

        Doctor existing = getDoctorById(id);

        existing.setName(doctor.getName());
        existing.setSpecialization(doctor.getSpecialization());
        existing.setEmail(doctor.getEmail());
        existing.setPhone(doctor.getPhone());

        return doctorRepository.save(existing);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}