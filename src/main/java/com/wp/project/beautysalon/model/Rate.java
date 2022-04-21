package com.wp.project.beautysalon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rate {

    @Id
    @GeneratedValue
    private Long rateId;

    @Column(length = 200)
    private String comment;

    @Column(nullable = false)
    private Integer rateValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    private SalonService ratedSalonService;

    public Rate() {
    }

    //Client smeneto so User
    public Rate(SalonService ratedSalonService, Integer rateValue, String comment, User client) {
        this.comment = comment;
        this.rateValue = rateValue;
        this.client = client;
        this.ratedSalonService = ratedSalonService;
    }
}
