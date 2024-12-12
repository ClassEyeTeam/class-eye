package com.classeye.studentservice.feign;

import com.classeye.studentservice.dto.feign.OptionResponse;
import com.classeye.studentservice.feign.impl.OptionFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author sejja
 **/
@FeignClient(name = "module-option-service", url = "http://localhost:8088/UNIVERSITY-SERVICE/options", fallback = OptionFeignClientFallback.class)

public interface OptionFeignClient {
    @GetMapping("/{id}")
    Optional<OptionResponse> getOptionById(@PathVariable("id") Long id);


}
