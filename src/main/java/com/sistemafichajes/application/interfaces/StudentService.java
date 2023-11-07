package com.sistemafichajes.application.interfaces;

import java.util.List;

public interface StudentService {
    StudentSimpleOutputDto addStudent(StudentInputDto Student);
    StudentFullOutputDto getFullStudentById(String id);
    StudentSimpleOutputDto getSimpleStudentById(String id);
    void deleteStudentById( String id);
    List<StudentSimpleOutputDto> getAllStudents();
    StudentSimpleOutputDto updateStudent(StudentInputDto Student);
}
