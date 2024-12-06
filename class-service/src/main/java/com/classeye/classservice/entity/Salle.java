package com.classeye.classservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Najat
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // may be the room number
    private int capacity;
    private SalleType salleType;
    @ManyToOne
    private Block block;

}
