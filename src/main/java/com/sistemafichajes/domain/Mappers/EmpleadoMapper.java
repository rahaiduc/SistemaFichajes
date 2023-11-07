package com.sistemafichajes.domain.Mappers;

import com.sistemafichajes.controller.dto.inputs.EmpleadoInputDto;
import com.sistemafichajes.controller.dto.inputs.PersonInputDto;
import com.sistemafichajes.domain.Empleado;
import com.sistemafichajes.domain.Persona;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    EmpleadoMapper INSTANCE= Mappers.getMapper(EmpleadoMapper.class);

    Empleado empleadoInputToEmpleado(EmpleadoInputDto profesorInputDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromEmpleadoDto(EmpleadoInputDto dto, @MappingTarget Empleado entity);


}
