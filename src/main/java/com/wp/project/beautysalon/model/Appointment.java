package com.wp.project.beautysalon.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private Integer totalPrice;

    @OneToOne
    private Payment payment;

    @OneToOne
    @JoinColumn(nullable = false)
    private Termin termin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User client;

    @ManyToMany
    private List<SalonService> salonServices;


    public Appointment(){}
}
