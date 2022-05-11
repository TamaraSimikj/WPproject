package com.wp.project.beautysalon.service;

import com.wp.project.beautysalon.model.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> listAll();
    Appointment findbyId(Integer Id);
}
