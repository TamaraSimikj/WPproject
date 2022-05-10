package com.wp.project.beautysalon.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer terminId;

    private LocalDateTime startTime;

    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User employee;

    @OneToOne
    private Appointment appointment;

    public Termin(LocalDateTime startTime, Integer duration, User employee) {
        this.startTime = startTime;
        this.duration = duration;
        this.employee = employee;
    }
    public Termin(){}
}
