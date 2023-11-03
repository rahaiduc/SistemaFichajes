package com.sistemafichajes.domain;

import com.sistemafichajes.controller.dto.outputs.AsignaturaOutputDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Asignatura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue(generator = "custom-string-id-generator")
    @GenericGenerator(name = "custom-string-id-generator", strategy = "com.sistemafichajes.domain.GeneradoresId.GeneradorIdPersona")
    private String id_Asignatura;

    private String asignatura;

    @ManyToMany
    @JoinTable(
            name = "asignatura_student",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students=new HashSet<>();;

    private String comments;

    @Column(nullable = false)
    private Date initial_date;

    private Date finish_date;

    public AsignaturaOutputDto AsignaturaToAsignaturaOutputDto(){
        return new AsignaturaOutputDto(
            this.id_Asignatura,
            this.asignatura,
            this.comments,
            this.initial_date,
            this.finish_date
        );
    }
}
