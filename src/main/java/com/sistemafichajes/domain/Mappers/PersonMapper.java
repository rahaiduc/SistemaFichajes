package com.sistemafichajes.domain.Mappers;

import com.sistemafichajes.controller.dto.inputs.PersonInputDto;
import com.sistemafichajes.domain.Persona;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE= Mappers.getMapper(PersonMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonFromDto(PersonInputDto dto, @MappingTarget Persona entity);

    PersonaEstudianteOutputDto personaToEstudianteDto(Persona p);

    PersonaProfesorOutputDto personaToProfesor(Persona p);
}
