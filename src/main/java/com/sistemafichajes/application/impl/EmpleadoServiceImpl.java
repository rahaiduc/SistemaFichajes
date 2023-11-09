package com.sistemafichajes.application.impl;

import com.sistemafichajes.controller.dto.inputs.EmpleadoInputDto;
import com.sistemafichajes.controller.dto.outputs.EmpleadoOutputDto;
import com.sistemafichajes.domain.Empleado;
import com.sistemafichajes.domain.Mappers.EmpleadoMapper;
import com.sistemafichajes.domain.Persona;
import com.sistemafichajes.repository.EmpleadoRepository;
import com.sistemafichajes.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;


    public EmpleadoOutputDto addEmpleado(EmpleadoInputDto empleadoInputDto) {
        if( empleadoInputDto.getBranch()==null){
            //Lanzo la excepcion para que la recoja el controlador y la maneje con un metodo handler
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,"Algun/os valores no pueden ser nulos");
        }
        Persona persona=personRepository.findById(empleadoInputDto.getId_persona()).orElseThrow();
        if (persona.getEmpleado() != null && persona.getEmpleado().getId_empleado() != null)throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY,"Esta persona ya tiene un empleado asignado");

        Empleado newEmpleado= EmpleadoMapper.INSTANCE.empleadoInputToEmpleado(empleadoInputDto);
        newEmpleado.setPersona(persona);
        // persona.setProfesor(newProfesor);
        return empleadoRepository.save(newEmpleado).EmpleadoToEmpleadoOutput();
    }

    public EmpleadoOutputDto getEmpleadoById(String id) {
        return empleadoRepository.findById(id).orElseThrow().EmpleadoToEmpleadoOutput();
    }

    public void deleteEmpleadoById(String id) {
        empleadoRepository.findById(id).orElseThrow();
        empleadoRepository.deleteById(id);
    }

    public List<EmpleadoOutputDto> getAllEmpleado() {
        return empleadoRepository.findAll()
                .stream()
                .map(Empleado::EmpleadoToEmpleadoOutput)
                .collect(Collectors.toList());
    }

    public EmpleadoOutputDto updateEmpleado(EmpleadoInputDto empleadoInputDto) {
        Empleado e=empleadoRepository.findById(empleadoInputDto.getId_empleado()).orElseThrow();
        EmpleadoMapper.INSTANCE.updateFromEmpleadoDto(empleadoInputDto,e);
        return empleadoRepository.save(e)
                .EmpleadoToEmpleadoOutput();
    }
}
