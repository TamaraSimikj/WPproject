package com.wp.project.beautysalon.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long paymentId;

    private String informations;

    @OneToOne
    private User client;
}
