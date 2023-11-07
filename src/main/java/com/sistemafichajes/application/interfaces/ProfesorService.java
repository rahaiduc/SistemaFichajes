package com.sistemafichajes.application.interfaces;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto Profesor);
    ProfesorOutputDto getProfesorById(String id);
    void deleteProfesorById( String id);
    List<ProfesorOutputDto> getAllProfesors();
    ProfesorOutputDto updateProfesor(ProfesorInputDto Profesor);
}
