package com.classeye.classservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Najat
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Block extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;
    private int floor;
    private int roomCount; // nombre de salles
    @OneToMany(mappedBy = "block")
    private List<Salle> salles;
}
