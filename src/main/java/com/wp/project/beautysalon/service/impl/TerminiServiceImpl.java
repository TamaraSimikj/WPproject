package com.wp.project.beautysalon.service.impl;

import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.model.Termin;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.model.exceptions.InvalidArgumentException;
import com.wp.project.beautysalon.model.exceptions.InvalidTerminIdException;
import com.wp.project.beautysalon.repository.AppointmentRepository;
import com.wp.project.beautysalon.repository.TerminRepository;
import com.wp.project.beautysalon.repository.UserRepository;
import com.wp.project.beautysalon.service.TerminiService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TerminiServiceImpl implements TerminiService {

    private final TerminRepository terminRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public TerminiServiceImpl(TerminRepository terminRepository, UserRepository userRepository, AppointmentRepository appointmentRepository) {
        this.terminRepository = terminRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Termin> findAll() {
        return this.terminRepository.findAll();
    }

    @Override
    public Termin findbyId(Integer id) {
        return this.terminRepository.findById(id).orElseThrow(InvalidArgumentException::new);
    }

    @Override
    public Termin create(LocalDateTime startTime, Integer duration, String employeeId) {
        User employee = this.userRepository.getById(employeeId);
        Termin termin = new Termin(startTime, duration, employee);
        return this.terminRepository.save(termin);
    }


    @Override
    public Termin update(Integer id, LocalDateTime startTime,  Integer duration, String employeeId) {

        Termin termin = this.terminRepository.findById(id).orElseThrow(InvalidTerminIdException::new);
        User employee = this.userRepository.getById(employeeId);
        termin.setStartTime(startTime);
        termin.setDuration(duration);
        termin.setEmployee(employee);

        return this.terminRepository.save(termin);
    }

    @Override
    public Termin delete(Integer id) {
        Termin termin  = this.findbyId(id);
        this.terminRepository.delete(termin);
        return termin;
    }
    @Override
    public List<Termin> listAllNotReserved() {

        return this.terminRepository.findAllByAppointmentNull();
    }
}
