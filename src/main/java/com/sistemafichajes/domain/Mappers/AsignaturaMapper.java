package com.sistemafichajes.domain.Mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface AsignaturaMapper {
    AsignaturaMapper INSTANCE=Mappers.getMapper(AsignaturaMapper.class);
    Asignatura asignaturaInputDtotoAsignatura(AsignaturaInputDto asignaturaInputDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatchAsignatura(AsignaturaInputDto dto, @MappingTarget Asignatura entity);
}
