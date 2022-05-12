package com.wp.project.beautysalon.service;

import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.model.SalonService;
import com.wp.project.beautysalon.model.User;

import java.util.List;

public interface AppointmentService {

    List<Appointment> listAll();

    Appointment findbyId(Integer Id);

    Appointment create(String clientId, Integer terminId, List<SalonService>services);

    Appointment update(Integer appointmentId,String clientId, Integer terminId, List<SalonService>services);

    Appointment delete(Integer id);

}
