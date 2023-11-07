package com.sistemafichajes.application.interfaces;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto Asignatura);
    AsignaturaOutputDto getAsignaturaById(String id);
    void deleteAsignaturaById( String id);
    List<AsignaturaOutputDto> getAllAsignaturas();
    AsignaturaOutputDto updateAsignatura(AsignaturaInputDto Asignatura);
}
