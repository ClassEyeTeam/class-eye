package com.classeye.universityservice.repository;

import com.classeye.universityservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author moham
 **/
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Optional<Department> getDepartmentByName(String name);
}
