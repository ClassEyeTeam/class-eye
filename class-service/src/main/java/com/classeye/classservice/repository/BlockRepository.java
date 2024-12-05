package com.classeye.classservice.repository;

import com.classeye.classservice.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Najat
 */
@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
    public Optional<Block> getBlockByName(String name); // Optional is a container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value.
    Optional<Block> findByName(String name);
}
