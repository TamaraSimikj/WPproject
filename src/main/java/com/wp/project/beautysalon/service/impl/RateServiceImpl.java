package com.wp.project.beautysalon.service.impl;

import com.wp.project.beautysalon.model.Rate;
import com.wp.project.beautysalon.model.exceptions.InvalidRateIdException;
import com.wp.project.beautysalon.repository.RateRepository;
import com.wp.project.beautysalon.service.RateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> listAll() {
        return this.rateRepository.findAll();
    }

    @Override
    public Rate findbyId(Long id) {
        return this.rateRepository.findById(id).orElseThrow(InvalidRateIdException::new);
    }

    @Override
    public Rate delete(Long id) {
        Rate rate = this.findbyId(id);
        this.rateRepository.delete(rate);
        return rate;
    }
}
