package com.classeye.studentservice.dto.response;

/**
 * @author moham
 **/
import java.time.LocalDateTime;
import java.util.List;

public record SessionResponseDTO(
        Long id,
        Long moduleOptionId,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        List<AttendanceResponseDTO> attendances
) {}

