package com.wp.project.beautysalon.repository;

import com.wp.project.beautysalon.model.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<SalonService,String> {

    SalonService findByServiceName(String serviceName);

}
