package com.sistemafichajes.domain.Mappers;

import com.sistemafichajes.controller.dto.inputs.StudentInputDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import com.sistemafichajes.domain.Student;
@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE= Mappers.getMapper(StudentMapper.class);

    Student studentInputDtoToStudent(StudentInputDto student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentInputDto dto, @MappingTarget Student entity);
}
