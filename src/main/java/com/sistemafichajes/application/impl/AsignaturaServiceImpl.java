package com.sistemafichajes.application.impl;

import com.sistemafichajes.application.interfaces.AsignaturaService;
import com.sistemafichajes.controller.dto.inputs.AsignaturaInputDto;
import com.sistemafichajes.controller.dto.outputs.AsignaturaOutputDto;
import com.sistemafichajes.domain.Asignatura;
import com.sistemafichajes.domain.Mappers.AsignaturaMapper;
import com.sistemafichajes.repository.AsignaturaRepository;
import com.sistemafichajes.repository.PersonRepository;
import com.sistemafichajes.repository.ProfesorRepository;
import com.sistemafichajes.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Override
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto Asignatura) {
        if( Asignatura.getAsignatura()==null || Asignatura.getAsignatura().isBlank() ||
                Asignatura.getInitial_date()==null){
            //Lanzo la excepcion para que la recoja el controlador y la maneje con un metodo handler
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,"Algun/os valores no pueden ser nulos");
        }
        Asignatura newAsignatura= AsignaturaMapper.INSTANCE.asignaturaInputDtotoAsignatura(Asignatura);
        return asignaturaRepository.save(newAsignatura).AsignaturaToAsignaturaOutputDto();
    }

    @Override
    public AsignaturaOutputDto getAsignaturaById(String id) {
        return asignaturaRepository.findById(id).orElseThrow().AsignaturaToAsignaturaOutputDto();
    }

    @Override
    public void deleteAsignaturaById(String id) {
        asignaturaRepository.findById(id).orElseThrow();
        asignaturaRepository.deleteById(id);
    }

    @Override
    public List<AsignaturaOutputDto> getAllAsignaturas() {
        return asignaturaRepository.findAll()
                .stream()
                .map(Asignatura::AsignaturaToAsignaturaOutputDto)
                .collect(Collectors.toList());
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(AsignaturaInputDto Asignatura) {
        Asignatura a=asignaturaRepository.findById(Asignatura.getId_asignatura()).orElseThrow();
        AsignaturaMapper.INSTANCE.updatePatchAsignatura(Asignatura,a);
        return asignaturaRepository.save(a).AsignaturaToAsignaturaOutputDto();
    }
}
