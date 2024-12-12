package com.classeye.studentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author moham
 **/
@Getter
@Setter
@Entity
public class Session extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long moduleOptionId;

    @Column(nullable = false)
    private LocalDateTime startDateTime; // Start date and time of the session

    @Column(nullable = false)
    private LocalDateTime endDateTime; // End date and time of the session

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Attendance> attendances = new ArrayList<>();
}

