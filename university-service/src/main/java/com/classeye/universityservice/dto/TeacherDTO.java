package com.classeye.universityservice.dto;

/**
 * @author sejja
 **/
public record TeacherDTO (
    long id,
    String name,
    String email,
    String phone,
    String address,
    DepartmentDto department
){
}
