package com.classeye.universityservice.repository;

import com.classeye.universityservice.entity.ModuleOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sejja
 **/
@Repository
public interface ModuleOptionRepository extends JpaRepository<ModuleOption, Long> {
    public List<ModuleOption> getModuleOptionByOptionId(Long optionId);
}
