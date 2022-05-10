package com.wp.project.beautysalon.repository;

import com.wp.project.beautysalon.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepository extends JpaRepository<Termin, Integer> {

   List<Termin> findAllByAppointmentNull();
}
