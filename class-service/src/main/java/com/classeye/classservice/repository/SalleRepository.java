package com.classeye.classservice.repository;

import com.classeye.classservice.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
    *
    * @author Najat
 */
@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
    public Optional<Salle> getSalleByName(String name);
    Optional<Salle> findByName(String name);
}
