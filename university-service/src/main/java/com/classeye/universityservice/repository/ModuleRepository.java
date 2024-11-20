package com.classeye.universityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.classeye.universityservice.entity.Module;
import java.util.Optional;

/**
 * @author sejja
 **/
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    public Optional<Module> getModuleByName(String name);

    boolean existsByName(String name);
}
