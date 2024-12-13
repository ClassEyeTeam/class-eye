package com.classeye.studentservice.service.specification;

import com.classeye.studentservice.entity.Attendance;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

/**
 * @author sejja
 **/

public class AttendanceSpecification {

    public static Specification<Attendance> hasStudentId(Long studentId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("student").get("id"), studentId);
    }

    public static Specification<Attendance> hasOptionId(Long optionId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("student").get("optionId"), optionId);
    }

    public static Specification<Attendance> isBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("session").get("startDateTime"), startDate, endDate);
    }
}
