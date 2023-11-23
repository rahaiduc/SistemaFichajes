package com.sistemafichajes.controller;

import com.sistemafichajes.application.impl.PersonServiceImpl;
import com.sistemafichajes.controller.dto.inputs.PersonInputDto;
import com.sistemafichajes.controller.dto.outputs.PersonOutputDto;
import com.sistemafichajes.domain.CustomError;
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

@CrossOrigin
@RestController
public class ControllerPersona {

    @Autowired
    PersonServiceImpl personService;



    @PostMapping("person")
    public ResponseEntity<PersonOutputDto> addPerson(@Valid @RequestBody PersonInputDto person) {
            URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personService.addPerson(person));
    }

    @GetMapping("person/{id}")
    public PersonOutputDto getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }


    @GetMapping("person/nombre/{nombre}")
    public List<Persona> getPersonByName(@PathVariable String nombre) {
        return personService.getPersonByName(nombre);
    }



    @GetMapping("person/getall")
    public List<PersonOutputDto> getAllPerson() {
        return personService.getAllPersons();
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<PersonOutputDto> deletePersonById(@PathVariable String id) {
        PersonOutputDto pod=personService.getPersonById(id);
        personService.deletePersonById(id);
        return ResponseEntity.ok().body(pod);
    }

    @PutMapping("person/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@PathVariable String id,@RequestBody PersonInputDto person) {
        person.setId_persona(id);
        return ResponseEntity.ok().body(personService.updatePerson(person));
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

