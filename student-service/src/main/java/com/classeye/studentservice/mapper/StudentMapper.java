package com.classeye.studentservice.mapper;

/**
 * @author moham
 **/
import com.classeye.studentservice.dto.request.StudentRequestDTO;
import com.classeye.studentservice.dto.response.StudentResponseDTO;
import com.classeye.studentservice.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);


    Student toEntity(StudentRequestDTO studentRequestDTO);

    @Mapping(target = "attendanceResponseDTO", ignore = true)
    StudentResponseDTO toDto(Student student);
}

