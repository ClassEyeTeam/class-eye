package com.classeye.studentservice.dto;

/**
 * @author sejja
 **/
public record TeacherDTO (
    long id,
    String name,
    String email,
    String phone,
    String address



){
    public TeacherDTO(long id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


}
