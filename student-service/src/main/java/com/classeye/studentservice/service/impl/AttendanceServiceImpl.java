package com.classeye.studentservice.service.impl;

import com.classeye.studentservice.dto.FaceDetectionRequestDTO;
import com.classeye.studentservice.dto.ModuleOptionResponseDTO;
import com.classeye.studentservice.dto.request.AttendanceRequestDTO;
import com.classeye.studentservice.dto.response.AttendanceResponseDTO;
import com.classeye.studentservice.entity.Attendance;
import com.classeye.studentservice.entity.AttendanceStatus;
import com.classeye.studentservice.entity.Session;
import com.classeye.studentservice.entity.Student;
import com.classeye.studentservice.feign.ModuleOptionFeignClient;
import com.classeye.studentservice.mapper.AttendanceMapper;
import com.classeye.studentservice.repository.AttendanceRepository;
import com.classeye.studentservice.repository.StudentRepository;
import com.classeye.studentservice.service.AttendanceService;
import com.classeye.studentservice.service.SessionService;
import com.classeye.studentservice.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final StudentRepository studentRepository;
    private final SessionService sessionService;
    private final ModuleOptionFeignClient moduleOptionFeignClient;


    @Override
    public AttendanceResponseDTO saveAttendance(AttendanceRequestDTO attendanceRequestDTO) {
        log.info("Saving attendance for student with ID: {}", attendanceRequestDTO.studentId());
        Attendance attendance = attendanceMapper.toEntity(attendanceRequestDTO);
        attendance.setStudent(studentRepository.findById(attendanceRequestDTO.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + attendanceRequestDTO.studentId())));

        attendance.setSession(sessionService.getSessionById(attendanceRequestDTO.sessionId()));
        Attendance savedAttendance = attendanceRepository.save(attendance);

        log.info("Attendance saved for student ID: {}", savedAttendance.getStudent().getId());
        return attendanceMapper.toDto(savedAttendance);
    }

    @Override
    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO attendanceRequestDTO) {
        log.info("Updating attendance for student with ID: {}", attendanceRequestDTO.studentId());
        Attendance attendance = getAttendanceById(id);
        attendance.setStudent(studentRepository.findById(attendanceRequestDTO.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + attendanceRequestDTO.studentId())));
        attendance.setSession(sessionService.getSessionById(attendanceRequestDTO.sessionId()));
        attendance.setStatus(attendanceRequestDTO.status());
        attendance.setStartTime(attendance.getStartTime());
        Attendance savedAttendance = attendanceRepository.save(attendance);

        log.info("Attendance saved for student ID: {}", savedAttendance.getStudent().getId());
        return attendanceMapper.toDto(savedAttendance);
    }

    @Override
    public AttendanceResponseDTO getAttendanceDtoById(Long id) {
        return attendanceMapper.toDto(getAttendanceById(id));
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        log.info("Fetching attendance with ID: {}", id);
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found with ID: " + id));
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendances() {
        log.info("Fetching all attendances");
        return attendanceRepository.findAll().stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAttendance(Long id) {
        log.info("Deleting attendance with ID: {}", id);
        if (!attendanceRepository.existsById(id)) {
            log.error("Attendance with ID {} not found", id);
            throw new EntityNotFoundException("Attendance not found with ID: " + id);
        }
        attendanceRepository.deleteById(id);
        log.info("Attendance deleted with ID: {}", id);
    }

    @Override
    public List<AttendanceResponseDTO> findByStudentId(Long studentId) {
        log.info("Fetching attendances for student ID: {}", studentId);
        return attendanceRepository.findByStudent_Id(studentId).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> findBySessionId(Long sessionId) {
        log.info("Fetching attendances for session ID: {}", sessionId);
        sessionService.getSessionById(sessionId);
        return attendanceRepository.findBySession_Id(sessionId).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> findByStatusAndStudentId(AttendanceStatus status, Long studentId) {
        log.info("Fetching attendances with status {} for student ID: {}", status, studentId);
        return attendanceRepository.findByStatusAndStudentId(status, studentId).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponseDTO findByStudentIdAndSessionId(Long studentId, Long sessionId) {
        log.info("Fetching attendances for student ID: {} and session ID: {}", studentId, sessionId);
        Attendance attendance = attendanceRepository.findByStudent_IdAndSession_Id(studentId, sessionId);
        return attendanceMapper.toDto(attendance);
    }

    @Override
    public void processFaceDetectionData(FaceDetectionRequestDTO faceDetectionRequestDTO) {
        Long studentId = faceDetectionRequestDTO.studentId();
        LocalDateTime timestamp = faceDetectionRequestDTO.timestamp();

        log.info("Processing face detection data for student ID: {}", studentId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));

        List<ModuleOptionResponseDTO> moduleOptionResponseDTO = moduleOptionFeignClient.getAllModulesInOption(student.getOptionId());
        for (ModuleOptionResponseDTO moduleOption : moduleOptionResponseDTO) {
            Optional<Session> sessionOpt = sessionService.findCurrentSessionForStudent(moduleOption.id(), timestamp);
            if (sessionOpt.isPresent()) {
                Session session = sessionOpt.get();
                Attendance attendance = attendanceRepository.findByStudent_IdAndSession_Id(studentId, session.getId());
                if (attendance == null) {
                    log.info("Recording attendance for student ID: {} in session ID: {}", studentId, session.getId());
                    Attendance newAttendance = new Attendance();
                    newAttendance.setStudent(student);
                    newAttendance.setSession(session);
                    newAttendance.setStatus(AttendanceStatus.PRESENT);
                    newAttendance.setStartTime(timestamp);
                    attendanceRepository.save(newAttendance);
                }
                break; // Exit the loop once the session is found and attendance is recorded
            }
        }
    }
}
