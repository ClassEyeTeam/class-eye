package com.classeye.universityservice.feign;

import com.classeye.universityservice.dto.RoomResponseDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

/**
 * @author sejja
 **/
public class FeignClientFallBack implements RoomFeignClient {
    @Override
    public Optional<RoomResponseDTO> getRoomById(Long id) {
        throw new EntityNotFoundException("Room not found");

    }
}
