package com.sistemafichajes.domain.Mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfesorMapper {
    ProfesorMapper INSTANCE= Mappers.getMapper(ProfesorMapper.class);
    Profesor profesorInputDtoToProfesor(ProfesorInputDto profesorInputDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfesorFromDto(ProfesorInputDto dto, @MappingTarget Profesor entity);
}
