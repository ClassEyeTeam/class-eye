package com.classeye.studentservice.feign;

import com.classeye.studentservice.dto.feign.ModuleOptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sejja
 **/
@FeignClient(name = "module-option-service", url = "http://localhost:8088/UNIVERSITY-SERVICE/module-options")
public interface ModuleOptionFeignClient {

    @GetMapping("/{id}")
    ModuleOptionResponse getModuleOptionById(@PathVariable("id") Long id);
}
