package com.classeye.universityservice.feign;


import com.classeye.universityservice.dto.RoomResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author sejja
 **/
@FeignClient(name = "class-service", url = "${class.service.url}/room", fallback = FeignClientFallBack.class)

public interface RoomFeignClient {
    @GetMapping("/{id}")
    Optional<RoomResponseDTO> getRoomById(@PathVariable("id") Long id);


}
