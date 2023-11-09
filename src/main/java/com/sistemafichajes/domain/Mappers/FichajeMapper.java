package com.sistemafichajes.domain.Mappers;

import com.sistemafichajes.controller.dto.inputs.EmpleadoInputDto;
import com.sistemafichajes.controller.dto.inputs.FichajeInputDto;
import com.sistemafichajes.domain.Empleado;
import com.sistemafichajes.domain.Fichaje;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FichajeMapper {
    FichajeMapper INSTANCE= Mappers.getMapper(FichajeMapper.class);

    Fichaje fichajeInputToFichaje(FichajeInputDto fichajeInputDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromFichajeDto(FichajeInputDto dto, @MappingTarget Fichaje entity);
}
