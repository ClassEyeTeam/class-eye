package com.classeye.universityservice.service;

import com.classeye.universityservice.dto.request.TeacherCreateDTO;
import com.classeye.universityservice.dto.response.TeacherModulesDTO;
import com.classeye.universityservice.dto.response.TeacherResponseDTO;
import com.classeye.universityservice.entity.Teacher;

import java.util.List;

/**
 * @author sejja
 **/

public interface TeacherService {
    TeacherResponseDTO createTeacher(TeacherCreateDTO teacherDTO);
    TeacherResponseDTO updateTeacher(Long id, TeacherCreateDTO teacherDTO);
    void deleteTeacher(Long id);
    TeacherModulesDTO getTeacherDtoById(Long id);
    Teacher getTeacherById(Long id);
    List<TeacherResponseDTO> getAllTeachers();
}
