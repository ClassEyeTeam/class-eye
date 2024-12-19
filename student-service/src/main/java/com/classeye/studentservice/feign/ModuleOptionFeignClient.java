package com.classeye.studentservice.feign;

import com.classeye.studentservice.dto.ModuleOptionResponseDTO;
import com.classeye.studentservice.dto.feign.ModuleOptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author sejja
 **/
@FeignClient(name = "module-option-service", url = "http://gateway-service:8088/UNIVERSITY-SERVICE/module-options")
public interface ModuleOptionFeignClient {

    @GetMapping("/{id}")
    ModuleOptionResponseDTO getModuleOptionById(@PathVariable("id") Long id);

    @GetMapping("/option/{id}")
    List<ModuleOptionResponseDTO> getAllModulesInOption(@PathVariable("id") Long id);
}
