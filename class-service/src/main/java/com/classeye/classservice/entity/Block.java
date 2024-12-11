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
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "block" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;
}
