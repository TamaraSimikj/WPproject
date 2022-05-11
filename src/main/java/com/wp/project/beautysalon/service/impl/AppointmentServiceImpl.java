package com.wp.project.beautysalon.service.impl;

import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.repository.AppointmentRepository;
import com.wp.project.beautysalon.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> listAll() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public Appointment findbyId(Integer Id) {
        return this.findbyId(Id);
    }
}
