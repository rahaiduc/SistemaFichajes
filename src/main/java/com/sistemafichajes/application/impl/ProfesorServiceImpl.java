package com.sistemafichajes.application.impl;

import com.sistemafichajes.application.interfaces.ProfesorService;
import com.sistemafichajes.controller.dto.inputs.ProfesorInputDto;
import com.sistemafichajes.controller.dto.outputs.ProfesorOutputDto;
import com.sistemafichajes.domain.Mappers.ProfesorMapper;
import com.sistemafichajes.domain.Persona;
import com.sistemafichajes.domain.Profesor;
import com.sistemafichajes.repository.AsignaturaRepository;
import com.sistemafichajes.repository.PersonRepository;
import com.sistemafichajes.repository.ProfesorRepository;
import com.sistemafichajes.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) {
        if( profesorInputDto.getBranch()==null || profesorInputDto.getBranch().isBlank()){
            //Lanzo la excepcion para que la recoja el controlador y la maneje con un metodo handler
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,"Algun/os valores no pueden ser nulos");
        }
        Persona persona=personRepository.findById(profesorInputDto.getId_persona()).orElseThrow();
        if (persona.getProfesor() != null && persona.getProfesor().getId_profesor() != null)throw new NoSuchElementException("Esta persona ya tiene un profesor asignado");
        if (persona.getStudent() != null && persona.getStudent().getId_student() != null)throw new NoSuchElementException("Esta persona es un estudiante");

        Profesor newProfesor=ProfesorMapper.INSTANCE.profesorInputDtoToProfesor(profesorInputDto);
        newProfesor.setPersona(persona);
       // persona.setProfesor(newProfesor);
        return profesorRepository.save(newProfesor).ProfesorToProfesorOutputDto();
    }

    @Override
    public ProfesorOutputDto getProfesorById(String id) {
        return profesorRepository.findById(id).orElseThrow().ProfesorToProfesorOutputDto();
    }

    @Override
    public void deleteProfesorById(String id) {
        profesorRepository.findById(id).orElseThrow();
        profesorRepository.deleteById(id);
    }

    @Override
    public List<ProfesorOutputDto> getAllProfesors() {
        return profesorRepository.findAll()
                .stream()
                .map(Profesor::ProfesorToProfesorOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto Profesor) {
        Profesor p=profesorRepository.findById(Profesor.getId_profesor()).orElseThrow();
        ProfesorMapper.INSTANCE.updateProfesorFromDto(Profesor,p);
        return profesorRepository.save(p)
                .ProfesorToProfesorOutputDto();
    }
}
