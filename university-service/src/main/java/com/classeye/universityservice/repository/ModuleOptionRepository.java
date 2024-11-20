package com.classeye.universityservice.repository;

import com.classeye.universityservice.entity.ModuleOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sejja
 **/
@Repository
public interface ModuleOptionRepository extends JpaRepository<ModuleOption, Long> {
}
