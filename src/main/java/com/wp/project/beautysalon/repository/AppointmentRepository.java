package com.wp.project.beautysalon.repository;

import com.wp.project.beautysalon.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
