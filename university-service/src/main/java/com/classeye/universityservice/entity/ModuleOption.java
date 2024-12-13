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

    @ManyToOne( optional = false)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne( optional = false)
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    @ManyToOne( optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
