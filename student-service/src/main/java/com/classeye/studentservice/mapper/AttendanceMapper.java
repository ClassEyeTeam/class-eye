package com.classeye.studentservice.mapper;

/**
 * @author moham
 **/
import com.classeye.studentservice.dto.request.AttendanceRequestDTO;
import com.classeye.studentservice.dto.response.AttendanceResponseDTO;
import com.classeye.studentservice.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "sessionId", target = "session.id")
    Attendance toEntity(AttendanceRequestDTO attendanceRequestDTO);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "session.id", target = "sessionId")
    AttendanceResponseDTO toDto(Attendance attendance);
}

