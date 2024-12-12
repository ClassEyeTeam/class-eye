package com.classeye.universityservice.repository;

import com.classeye.universityservice.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author sejja
 **/
@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    public Optional<Option> getOptionByName(String name);

    Optional<List<Option>> getOptionsByDepartmentId(Long id);
}
