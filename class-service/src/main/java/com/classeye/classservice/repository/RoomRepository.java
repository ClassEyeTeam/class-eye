package com.classeye.classservice.repository;

import com.classeye.classservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
    *
    * @author Najat
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    public Optional<Room> findByName (String name);
    public Optional<Room> findByNameAndBlockId (String name,Long id);
}
