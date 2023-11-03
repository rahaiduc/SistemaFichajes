package com.sistemafichajes.domain;

import com.sistemafichajes.controller.dto.inputs.PersonInputDto;
import com.sistemafichajes.controller.dto.outputs.PersonOutputDto;
import com.sistemafichajes.controller.dto.outputs.PersonaEstudianteOutputDto;
import com.sistemafichajes.controller.dto.outputs.PersonaProfesorOutputDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Table(name="Persona")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @jakarta.persistence.Id
    @Id
    @Column(name = "id_persona")
    @GeneratedValue(generator = "custom-string-id-generator")
    @GenericGenerator(name = "custom-string-id-generator", strategy = "com.sistemafichajes.domain.GeneradoresId.GeneradorIdPersona")
    private String id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private Student student;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profesor profesor;



    public Persona(PersonInputDto personInputDto) {
        this.id_persona = personInputDto.getId_persona();
        this.name = personInputDto.getName();
        this.surname= personInputDto.getSurname();
        this.company_email =personInputDto.getCompany_email();
        this.usuario=personInputDto.getUsuario();
        this.password=personInputDto.getPassword();
        this.personal_email=personInputDto.getPersonal_email();
        this.active=personInputDto.isActive();
        this.city=personInputDto.getCity();
        this.created_date=personInputDto.getCreated_date();
        this.imagen_url=personInputDto.getImagen_url();
        this.termination_date=personInputDto.getTermination_date();
    }

    public Persona(String id_persona, String usuario, String password, String name, String surname, String company_email, String personal_email, String city, Date created_date) {
        this.id_persona = id_persona;
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.company_email = company_email;
        this.personal_email = personal_email;
        this.city = city;
        this.created_date = created_date;
    }

    public PersonOutputDto personToPersonOutputDto() {
        return new PersonOutputDto(
                this.id_persona,
                this.usuario,
                this.name,
                this.surname,
                this.company_email,
                this.active
        );
    }

    public PersonaProfesorOutputDto personToPersonProfesorOutputDto() {
        PersonaProfesorOutputDto personaProfesorOutputDto=new PersonaProfesorOutputDto(this.profesor.ProfesorToProfesorOutputDto());
        personaProfesorOutputDto.setId_persona(this.id_persona);
        personaProfesorOutputDto.setName(this.name);
        personaProfesorOutputDto.setSurname(this.surname);
        personaProfesorOutputDto.setCompany_email(this.company_email);
        personaProfesorOutputDto.setUsuario(this.usuario);
        personaProfesorOutputDto.setActive(this.active);
        return  personaProfesorOutputDto;
    }

    public PersonaEstudianteOutputDto personToPersonaEstudianteOutputDto() {
        PersonaEstudianteOutputDto personaEstudianteOutputDto=new PersonaEstudianteOutputDto(this.student.studentToStudentFulltOutputDto());
        personaEstudianteOutputDto.setId_persona(this.id_persona);
        personaEstudianteOutputDto.setName(this.name);
        personaEstudianteOutputDto.setSurname(this.surname);
        personaEstudianteOutputDto.setCompany_email(this.company_email);
        personaEstudianteOutputDto.setUsuario(this.usuario);
        personaEstudianteOutputDto.setActive(this.active);
        return  personaEstudianteOutputDto;
    }


    public void setId(String id) {
        this.id_persona = id;
    }



}

