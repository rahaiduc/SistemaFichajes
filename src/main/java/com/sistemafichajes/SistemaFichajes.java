package com.sistemafichajes;

import com.sistemafichajes.controller.dto.inputs.EmpleadoInputDto;
import com.sistemafichajes.domain.BranchType;
import com.sistemafichajes.domain.Empleado;
import com.sistemafichajes.domain.Mappers.EmpleadoMapper;
import com.sistemafichajes.domain.Persona;
import com.sistemafichajes.repository.EmpleadoRepository;
import com.sistemafichajes.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Date;

@EnableFeignClients
@SpringBootApplication
public class SistemaFichajes {

    public static void main(String[] args) {
        SpringApplication.run(SistemaFichajes.class, args);
    }

    @Autowired
    PersonRepository personRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;

    @PostConstruct
    public void populateDb() {

        personRepository.save(new Persona("1","user1","pass1", "name1","Martinez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("2","user2","pass2", "name2", "Martin","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("3","user3","pass3", "name3", "Gonzalez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("4","user4","pass4", "name4","Fernandez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("5","user5","pass5", "name5","Gutierrez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("6","user6","pass6", "name6","San Martin","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("7","user7","pass7", "name7","Martinez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("8","user8","pass8", "name8", "Martin","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("9","user9","pass9", "name9", "Gonzalez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("10","user10","pass10", "name10","Fernandez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("11","user11","pass11", "name11","Gutierrez","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        personRepository.save(new Persona("12","user12","pass12", "name12","San Martin","abc@gmail.com","abc@gmail.com","Vallecas", new Date()));
        EmpleadoInputDto empleadoInputDto=new EmpleadoInputDto("1","1", "Front");
        Empleado empleado= EmpleadoMapper.INSTANCE.empleadoInputToEmpleado(empleadoInputDto);
        empleado.setPersona(personRepository.findById(empleadoInputDto.getId_persona()).orElseThrow());
        empleadoRepository.save(empleado);
        EmpleadoInputDto empleadoInputDto2=new EmpleadoInputDto("2","2", "Front");
        Empleado empleado2= EmpleadoMapper.INSTANCE.empleadoInputToEmpleado(empleadoInputDto2);
        empleado2.setPersona(personRepository.findById(empleadoInputDto2.getId_persona()).orElseThrow());
        empleadoRepository.save(empleado2);
    }

}
