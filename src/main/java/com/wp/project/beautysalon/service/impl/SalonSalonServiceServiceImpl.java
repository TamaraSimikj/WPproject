package com.wp.project.beautysalon.service.impl;
import com.wp.project.beautysalon.model.Rate;
import com.wp.project.beautysalon.model.User;
import com.wp.project.beautysalon.model.exceptions.InvalidSalonServiceIdException;
import com.wp.project.beautysalon.model.exceptions.ServiceIdReservedException;
import com.wp.project.beautysalon.repository.RateRepository;
import com.wp.project.beautysalon.repository.ServiceRepository;
import com.wp.project.beautysalon.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.wp.project.beautysalon.service.SalonServiceService;

import com.wp.project.beautysalon.model.SalonService;

import java.util.List;

@Service
public class SalonSalonServiceServiceImpl implements SalonServiceService {

    private final ServiceRepository serviceRepository;
    private final RateRepository rateRepository;
    private final UserRepository userRepository;

    public SalonSalonServiceServiceImpl(ServiceRepository serviceRepository, RateRepository rateRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.rateRepository = rateRepository;
        this.userRepository = userRepository;

    }

    @Override
    public List<SalonService> findAll() {
        return this.serviceRepository.findAll();
    }

    @Override
    public SalonService findbyId(String id) {
        return this.serviceRepository.findById(id).orElseThrow(InvalidSalonServiceIdException::new);
    }

    @Override
    public List<SalonService> findAllById(List<String> servicesIds) {
        return this.serviceRepository.findAllById(servicesIds);
    }

    @Override
    public SalonService findbyserviceName(String serviceName) {
        return this.serviceRepository.findByServiceName(serviceName);
    }

    @Override
    public SalonService create(String id, String serviceName, Integer price, List<String> employeeIds) {
        try {
            SalonService postoeckaUsluga = this.findbyId(id);
            throw new ServiceIdReservedException("This id is reserved,try with another!");
        }
        catch (InvalidSalonServiceIdException ex){
            List<User> employees = this.userRepository.findAllById(employeeIds);
            SalonService salonService = new SalonService(id,serviceName,price,employees);
            return this.serviceRepository.save(salonService);
        }
    }

    @Override
    public SalonService update(String id, String serviceName, Integer price, List<String> employeeIds) {
        SalonService salonService = this.findbyId(id);
        salonService.setServiceName(serviceName);
        salonService.setPrice(price);

        List<User> employees = this.userRepository.findAllById(employeeIds);
        salonService.setEmployees(employees);

        return this.serviceRepository.save(salonService);
    }

//    @Override
//    public SalonService create(String id, String serviceName, Integer price, List<Long> employeeIds) {
//        try {
//            SalonService postoeckaUsluga = this.findbyId(id);
//            throw new ServiceIdReservedException("This id is reserved,try with another!");
//        }
//        catch (InvalidSalonServiceIdException ex){
//            List<User> employees = this.userRepository.f.findAllById(employeeIds);
//            SalonService salonService = new SalonService(id,serviceName,price,employees);
//            return this.serviceRepository.save(salonService);
//        }
//    }

//    @Override
//    public SalonService update(String id, String serviceName, Integer price, List<Long> employeeIds) {
//        SalonService salonService = this.findbyId(id);
//        salonService.setServiceName(serviceName);
//        salonService.setPrice(price);
//    //    List<Employee> employees = this.employeeRepository.findAllById(employeeIds);
//       // salonService.setEmployees(employees);
//        return this.serviceRepository.save(salonService);
//    }

    @Override
    public SalonService delete(String id) {
        SalonService salonService = this.findbyId(id);
        this.serviceRepository.delete(salonService);
        return salonService;
    }

    @Override
    public void rate(SalonService salonService, Integer value, String comment, User client) {
        Rate rate = new Rate(salonService,value,comment,client);
        this.rateRepository.save(rate);
    }
}
