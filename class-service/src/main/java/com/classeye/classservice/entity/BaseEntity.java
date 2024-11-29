package com.classeye.classservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass // this annotation is used to specify that the class is an entity class that is a superclass for other entity classes
@EntityListeners(AuditingEntityListener.class) // this annotation is used to specify the entity listener class that will be used to listen to the entity class events
public abstract class BaseEntity implements Serializable {

    @CreatedDate
    @Column(updatable = false,nullable = false) // this annotation is used to specify that the column is not updatable and nullable we can add more attributes to this annotation
    private LocalDateTime createdAt;
    @CreatedDate
    @Column()
    private LocalDateTime updatedAt;
}
