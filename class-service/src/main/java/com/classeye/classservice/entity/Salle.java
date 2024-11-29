package com.classeye.classservice.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String nom;
    private int capacite;
    private SalleType salleType;
    @ManyToOne
    private Block block;

}
