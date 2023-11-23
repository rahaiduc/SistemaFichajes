package com.sistemafichajes.application.impl;

import com.sistemafichajes.controller.dto.inputs.EmpleadoInputDto;
import com.sistemafichajes.controller.dto.inputs.FichajeInputDto;
import com.sistemafichajes.controller.dto.outputs.EmpleadoOutputDto;
import com.sistemafichajes.controller.dto.outputs.FichajeOutputDto;
import com.sistemafichajes.domain.Empleado;
import com.sistemafichajes.domain.Fichaje;
import com.sistemafichajes.domain.Mappers.EmpleadoMapper;
import com.sistemafichajes.domain.Mappers.FichajeMapper;
import com.sistemafichajes.domain.Persona;
import com.sistemafichajes.repository.EmpleadoRepository;
import com.sistemafichajes.repository.FichajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class FichajeServiceImpl {
    @Autowired
    FichajeRepository fichajeRepository;

    @Autowired
    EmpleadoRepository empleadoRepository;

    public FichajeOutputDto addFichaje(String empleadoId) {
        Empleado empleado=empleadoRepository.findById(empleadoId).orElseThrow();

        Fichaje fichaje= new Fichaje();
        fichaje.setEmpleado(empleado);
        fichaje.setTimeEntry(new Date().getTime());
        fichaje.setTimeExit(0);
        return fichajeRepository.save(fichaje).FichajeToFichajeOutput();
    }

    public FichajeOutputDto getFichajeEntrada(String empleadoId) {
        Fichaje fichaje=fichajeRepository.findTopByEmpleadoOrderByTimeEntryDesc(empleadoId).orElseThrow(()->new NoSuchElementException("No existe ningun fichaje"));
        if(fichaje.getTimeExit()!=0){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Hay que registrar la entrada");
        }{
            fichaje.setTimeExit(new Date().getTime());
            fichajeRepository.save(fichaje);
        }
        return fichaje.FichajeToFichajeOutput();
    }
}
