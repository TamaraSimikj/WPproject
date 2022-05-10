package com.wp.project.beautysalon.service;

import com.wp.project.beautysalon.model.Termin;

import java.time.LocalDateTime;
import java.util.List;

public interface TerminiService {

    List<Termin> findAll();

    Termin findbyId(Integer id);

     Termin create(LocalDateTime startTime,Integer duration, String employeeId);

     Termin update(Integer id, LocalDateTime startTime,Integer duration, String employeeId);

     Termin delete(Integer id);

    List<Termin> listAllNotReserved();

}
