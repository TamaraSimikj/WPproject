package com.wp.project.beautysalon.service;

import com.wp.project.beautysalon.model.SalonService;
import com.wp.project.beautysalon.model.User;

import java.util.List;

public interface SalonServiceService {

    List<SalonService> findAll();

    SalonService findbyId(String id);

    List<SalonService> findAllById(List<String> servicesIds);

    SalonService findbyserviceName(String serviceName);

   SalonService create(String id, String serviceName, Integer price, List<String> employeeIds);

    SalonService update(String id, String serviceName, Integer price , List<String> employeeIds);

    SalonService delete(String id);

    void rate(SalonService service, Integer value, String comment, User client);


}
