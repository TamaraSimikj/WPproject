package com.wp.project.beautysalon.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @NotNull
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

    public Appointment(User client, Termin termin, List<SalonService> salonServices) {
        this.termin = termin;
        this.client = client;
        this.salonServices = salonServices;
        this.totalPrice = salonServices.stream().map(x -> x.getPrice())
                .reduce(0, Integer::sum);
    }

    public Termin getTermin() {
        return termin;
    }
}
