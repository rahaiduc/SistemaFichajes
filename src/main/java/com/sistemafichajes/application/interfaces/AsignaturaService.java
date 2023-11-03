package com.sistemafichajes.application.interfaces;

import com.sistemafichajes.controller.dto.inputs.AsignaturaInputDto;
import com.sistemafichajes.controller.dto.outputs.AsignaturaOutputDto;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto Asignatura);
    AsignaturaOutputDto getAsignaturaById(String id);
    void deleteAsignaturaById( String id);
    List<AsignaturaOutputDto> getAllAsignaturas();
    AsignaturaOutputDto updateAsignatura(AsignaturaInputDto Asignatura);
}
