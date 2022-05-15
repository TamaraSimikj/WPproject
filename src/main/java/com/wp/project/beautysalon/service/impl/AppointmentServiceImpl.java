package com.wp.project.beautysalon.service.impl;

import com.wp.project.beautysalon.model.Appointment;
import com.wp.project.beautysalon.model.SalonService;
import com.wp.project.beautysalon.model.Termin;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.model.exceptions.InvalidAppointmentId;
import com.wp.project.beautysalon.model.exceptions.InvalidTerminIdException;
import com.wp.project.beautysalon.model.exceptions.InvalidUsernameException;
import com.wp.project.beautysalon.repository.AppointmentRepository;
import com.wp.project.beautysalon.repository.TerminRepository;
import com.wp.project.beautysalon.repository.UserRepository;
import com.wp.project.beautysalon.service.AppointmentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final TerminRepository terminRepository;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, TerminRepository terminRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.terminRepository = terminRepository;
    }

    @Override
    public List<Appointment> listAll() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public Appointment findbyId(Integer Id) {

        return this.appointmentRepository.findById(Id).orElseThrow(InvalidAppointmentId::new);
    }

    @Override
    @Transactional
    public Appointment create(String clientId, Integer terminId, List<SalonService> services) {
        User client = this.userRepository.findById(clientId).orElseThrow(InvalidUsernameException::new);
        Termin termin = this.terminRepository.findById(terminId).orElseThrow(InvalidTerminIdException::new);

        Appointment appointment = new Appointment(client, termin, services);
        this.appointmentRepository.save(appointment);

        termin.setAppointment(appointment);
        this.terminRepository.save(termin);
        return appointment;
    }

    @Override
    public Appointment update(Integer appointmentId, String clientId, Integer terminId, List<SalonService> services) {
        Appointment appointment = this.findbyId(appointmentId);
        User client = this.userRepository.findById(clientId).orElseThrow(InvalidUsernameException::new);
        Termin termin = this.terminRepository.findById(terminId).orElseThrow(InvalidTerminIdException::new);
        appointment.setClient(client);
        appointment.setSalonServices(services);
        appointment.setTermin(termin);
        return appointment;
    }

    @Override
    @Transactional
    public Appointment delete(Integer id) {
        Appointment appointment = this.findbyId(id);
        Termin termin = this.terminRepository.getById(appointment.getTermin().getTerminId());
        termin.setAppointment(null);
        this.terminRepository.save(termin);
        this.appointmentRepository.delete(appointment);

        return appointment;
    }
}
