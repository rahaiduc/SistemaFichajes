package com.sistemafichajes.application.interfaces;

import com.sistemafichajes.controller.dto.inputs.StudentInputDto;
import com.sistemafichajes.controller.dto.outputs.StudentFullOutputDto;
import com.sistemafichajes.controller.dto.outputs.StudentSimpleOutputDto;

import java.util.List;

public interface StudentService {
    StudentSimpleOutputDto addStudent(StudentInputDto Student);
    StudentFullOutputDto getFullStudentById(String id);
    StudentSimpleOutputDto getSimpleStudentById(String id);
    void deleteStudentById( String id);
    List<StudentSimpleOutputDto> getAllStudents();
    StudentSimpleOutputDto updateStudent(StudentInputDto Student);
}
