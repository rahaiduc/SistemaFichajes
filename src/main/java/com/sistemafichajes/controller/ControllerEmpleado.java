package com.sistemafichajes.controller;

import com.sistemafichajes.application.impl.EmpleadoServiceImpl;
import com.sistemafichajes.application.impl.FichajeServiceImpl;
import com.sistemafichajes.application.impl.PersonServiceImpl;
import com.sistemafichajes.controller.dto.inputs.EmpleadoInputDto;
import com.sistemafichajes.controller.dto.inputs.FichajeInputDto;
import com.sistemafichajes.controller.dto.inputs.PersonInputDto;
import com.sistemafichajes.controller.dto.outputs.EmpleadoOutputDto;
import com.sistemafichajes.controller.dto.outputs.FichajeOutputDto;
import com.sistemafichajes.controller.dto.outputs.PersonOutputDto;
import com.sistemafichajes.domain.CustomError;
import com.sistemafichajes.domain.Fichaje;
import com.sistemafichajes.domain.Persona;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/empleado")
public class ControllerEmpleado {

    @Autowired
    EmpleadoServiceImpl empleadoService;
    @Autowired
    FichajeServiceImpl fichajeService;



    @PostMapping
    public ResponseEntity<EmpleadoOutputDto> addEmpleado(@Valid @RequestBody EmpleadoInputDto empleadoInputDto) {
        URI location = URI.create("/empleado");
        return ResponseEntity.created(location).body(empleadoService.addEmpleado(empleadoInputDto));
    }

   /* @GetMapping("empleado/nombre/{nombre}")
    public List<EmpleadoOutputDto> getEmpleadoByName(@PathVariable String nombre) {
        return empleadoService.getEmpleadorById(nombre);
    }*/

    @GetMapping("/{id}")
    public EmpleadoOutputDto getEmpleadoById(@PathVariable String id) {
        return empleadoService.getEmpleadoById(id);
    }

    @GetMapping("/getall")
    public List<EmpleadoOutputDto> getAllEmpleado() {
        return empleadoService.getAllEmpleado();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpleadoOutputDto> deleteEmpleadoById(@PathVariable String id) {
        EmpleadoOutputDto eod=empleadoService.getEmpleadoById(id);
        empleadoService.deleteEmpleadoById(id);
        return ResponseEntity.ok().body(eod);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoOutputDto> updateEmpleado(@PathVariable String id,@RequestBody EmpleadoInputDto empleadoInputDto) {
        empleadoInputDto.setId_empleado(id);
        return ResponseEntity.ok().body(empleadoService.updateEmpleado(empleadoInputDto));
    }

    //FICHAJES
    @PostMapping("/fichaje/{id}")
    public FichajeOutputDto addfichaje(@Valid @PathVariable String id) {
        URI location = URI.create("/empleado");
        return fichajeService.addFichaje(id);
    }

    //CONTROL DE EXCEPCIONES
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CustomError> handleMissingParametersExceptions(MissingServletRequestParameterException ex) {
        CustomError ce = new CustomError();
        ce.setTimestamp(new Date());
        ce.setHttpCode(HttpStatus.BAD_REQUEST.value());
        ce.setMensaje("Error 400-Faltan parametros por enviar en la Query Params");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ce);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException() {
        NoSuchElementException ne=new NoSuchElementException("404-Persona no encontrada");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ne.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException() {
        CustomError ce = new CustomError();
        ce.setTimestamp(new Date());
        ce.setHttpCode(HttpStatus.NOT_FOUND.value());
        ce.setMensaje("Error 404 - Persona no encontrada");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ce);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntity(HttpClientErrorException hee) {
        CustomError ce = new CustomError();
        ce.setTimestamp(new Date());
        ce.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        ce.setMensaje(hee.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ce);
    }

}
