package com.classeye.universityservice.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author sejja
 **/
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many ModuleOptions can belong to one Module
    @JoinColumn(name = "module_id", nullable = false) // Defines the foreign key column
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many ModuleOptions can belong to one Option
    @JoinColumn(name = "option_id", nullable = false) // Defines the foreign key column
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Many ModuleOptions can belong to one Teacher
    @JoinColumn(name = "teacher_id", nullable = false) // Defines the foreign key column
    private Teacher teacher;
}
