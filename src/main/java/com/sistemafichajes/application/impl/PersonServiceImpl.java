package com.sistemafichajes.application.impl;

import com.sistemafichajes.application.interfaces.PersonService;
import com.sistemafichajes.controller.dto.inputs.PersonInputDto;
import com.sistemafichajes.controller.dto.outputs.PersonOutputDto;
import com.sistemafichajes.domain.Mappers.PersonMapper;
import com.sistemafichajes.domain.Persona;
import com.sistemafichajes.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;


    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {
        //Validación de nulos
        if( person.getName()==null || person.getName().isBlank()  ||
            person.getCompany_email()==null || person.getCompany_email().isBlank() ||
            person.getUsuario()==null || person.getUsuario().isBlank()  ||
            person.getPersonal_email()==null || person.getPersonal_email().isBlank() ||
            person.getCity()==null || person.getCity().isBlank() ||
            person.getCreated_date()==null ||
            person.getPassword()==null || person.getPassword().isBlank() ){
            //Lanzo la excepcion para que la recoja el controlador y la maneje con un metodo handler
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Algun/os valores no pueden ser nulos");
        }
        //Validacion caracteres en el campo usuario
        if(person.getUsuario().length()>10 || person.getUsuario().length()<6){
            //Lanzo la excepcion para que la recoja el controlador y la maneje con un metodo handler
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"El usuario tiene que tener entre 6 y 10 caracteres");
        }
        return personRepository.save(new Persona(person))
                .personToPersonOutputDto();
    }
    @Override
    public PersonOutputDto getPersonById(String id){
        return personRepository.findById(id).orElseThrow()
                .personToPersonOutputDto();
    }

    public PersonOutputDto searchPersonById(String id){
        Persona p=personRepository.findById(id).orElseThrow();
        if(p.getStudent()!=null){
            return p.personToPersonaEstudianteOutputDto();
        }else if(p.getProfesor()!=null){
            return p.personToPersonProfesorOutputDto();
        }else{
            return p.personToPersonOutputDto();
        }
    }


    public List<Persona> getPersonByName(String nombre){
        return personRepository.findByName(nombre).stream().toList();
    }

    @Override
    public void deletePersonById(String id) {
        personRepository.findById(id).orElseThrow();
        personRepository.deleteById(id);
    }
    @Override
    public List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personToPersonOutputDto).toList();
    }
    public List<PersonOutputDto> getAllPersons() {
        return personRepository.findAll().stream().map(Persona::personToPersonOutputDto).collect(Collectors.toList());
    }
    @Override
    public PersonOutputDto updatePerson(PersonInputDto person) {
        Persona p=personRepository.findById(person.getId_persona()).orElseThrow();
        PersonMapper.INSTANCE.updatePersonFromDto(person,p);
        return personRepository.save(p)
                .personToPersonOutputDto();
    }

    public List<PersonOutputDto> getGreaterQuery(HashMap<String, Object> data){
        PageRequest pageRequest=PageRequest.of((Integer)data.get("pageNumber"),(Integer)data.get("pageSize"));
        return personRepository.getGreaterQuery(data,pageRequest);
    }
    public List<PersonOutputDto> getLessQuery(HashMap<String, Object> data){
        PageRequest pageRequest=PageRequest.of((Integer)data.get("pageNumber"),(Integer)data.get("pageSize"));
        return personRepository.getLessQuery(data,pageRequest);
    }
}