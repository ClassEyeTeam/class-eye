package com.classeye.studentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author moham
 **/
@Getter
@Setter
@Entity
public class Seance extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    private LocalTime heure;
    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL)
    private List<Presence> presences = new ArrayList<>();
}
