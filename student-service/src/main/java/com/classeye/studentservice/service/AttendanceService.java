package com.classeye.studentservice.service;

/**
 * @author moham
 **/

import com.classeye.studentservice.dto.FaceDetectionRequestDTO;
import com.classeye.studentservice.dto.request.AttendanceRequestDTO;
import com.classeye.studentservice.dto.response.AttendanceResponseDTO;
import com.classeye.studentservice.entity.Attendance;
import com.classeye.studentservice.entity.AttendanceStatus;

import java.util.List;

public interface AttendanceService {

    // CRUD Operations
    AttendanceResponseDTO saveAttendance(AttendanceRequestDTO attendance);

    AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO attendance);

    AttendanceResponseDTO getAttendanceDtoById(Long id);
    Attendance getAttendanceById(Long id);

    List<AttendanceResponseDTO> getAllAttendances();

    void deleteAttendance(Long id);

    // Custom Methods
    List<AttendanceResponseDTO> findByStudentId(Long studentId);

    List<AttendanceResponseDTO> findBySessionId(Long sessionId);

    List<AttendanceResponseDTO> findByStatusAndStudentId(AttendanceStatus status, Long studentId);

    AttendanceResponseDTO findByStudentIdAndSessionId(Long studentId, Long sessionId);

    void processFaceDetectionData(FaceDetectionRequestDTO faceDetectionRequestDTO);
}

