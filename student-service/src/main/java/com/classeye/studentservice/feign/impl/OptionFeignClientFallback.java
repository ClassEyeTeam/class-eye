package com.classeye.studentservice.feign.impl;

import com.classeye.studentservice.dto.feign.OptionResponse;
import com.classeye.studentservice.feign.OptionFeignClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author sejja
 **/

@Component
public class OptionFeignClientFallback implements OptionFeignClient {
    @Override
    public Optional<OptionResponse> getOptionById(Long id) {
        // Return a default response or handle the error as needed
        return Optional.empty();
    }
}
