package com.wp.project.beautysalon.service;

import com.wp.project.beautysalon.model.Rate;

import java.util.List;

public interface RateService {

    List<Rate> listAll();

    Rate findbyId(Long id);

    Rate delete(Long id);


}
