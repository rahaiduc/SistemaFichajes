package com.sistemafichajes.application.interfaces;

import com.sistemafichajes.controller.dto.inputs.ProfesorInputDto;
import com.sistemafichajes.controller.dto.outputs.ProfesorOutputDto;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto Profesor);
    ProfesorOutputDto getProfesorById(String id);
    void deleteProfesorById( String id);
    List<ProfesorOutputDto> getAllProfesors();
    ProfesorOutputDto updateProfesor(ProfesorInputDto Profesor);
}
