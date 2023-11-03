package com.sistemafichajes.controller;

import com.sistemafichajes.application.impl.ProfesorServiceImpl;
import com.sistemafichajes.controller.dto.inputs.ProfesorInputDto;
import com.sistemafichajes.controller.dto.outputs.ProfesorOutputDto;
import com.sistemafichajes.domain.CustomError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("profesor")
public class ControllerProfesor {
    @Autowired
    ProfesorServiceImpl profesorService;

    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addProfesor(@Valid @RequestBody ProfesorInputDto profesor) {
            URI location = URI.create("/profesor");
            return ResponseEntity.created(location).body(profesorService.addProfesor(profesor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorById(@PathVariable String id) {
            return ResponseEntity.ok().body(profesorService.getProfesorById(id));
    }



    @GetMapping
    public List<ProfesorOutputDto> getAllProfesors() {
            return profesorService.getAllProfesors();
    }

    @DeleteMapping("/{id}")
    public void deleteProfesirById(@PathVariable String id) {
            profesorService.deleteProfesorById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> updateProfesor(@PathVariable String id,@RequestBody ProfesorInputDto profesor) {
            profesor.setId_profesor(id);
            return ResponseEntity.ok().body(profesorService.updateProfesor(profesor));
    }


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

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException nsee) {
        NoSuchElementException ne=new NoSuchElementException("404-Profesor no encontrada");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(nsee.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException() {
        CustomError ce = new CustomError();
        ce.setTimestamp(new Date());
        ce.setHttpCode(HttpStatus.NOT_FOUND.value());
        ce.setMensaje("Error 404 - Profesor no encontrada");
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
