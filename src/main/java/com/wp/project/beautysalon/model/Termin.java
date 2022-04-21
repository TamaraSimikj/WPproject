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

    private Float duration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User employee;
}
