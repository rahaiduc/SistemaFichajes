package com.sistemafichajes.domain;

import com.sistemafichajes.controller.dto.outputs.StudentFullOutputDto;
import com.sistemafichajes.controller.dto.outputs.StudentSimpleOutputDto;
import com.sistemafichajes.controller.dto.outputs.StudentsAsignaturas;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="Estudiante")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "custom-string-id-generator")
    @GenericGenerator(name = "custom-string-id-generator", strategy = "com.sistemafichajes.domain.GeneradoresId.GeneradorIdPersona")
    private String id_student;

    @OneToOne
    @JoinColumn(name="id_persona", nullable = false, unique = true)
    private Persona persona;

    @Column(nullable = false)
    private int num_hours_week;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "profesor_id_profesor")
    private Profesor profesor;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'FRONT'", nullable = false)
    private BranchType branch;

    @ManyToMany
    @JoinTable(
            name = "asignatura_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private Set<Asignatura> asignaturas=new HashSet<>();;


    public StudentFullOutputDto studentToStudentFulltOutputDto(){
        return new StudentFullOutputDto(
                this.id_student,
                this.num_hours_week,
                this.comments,
                this.branch,
                this.persona.personToPersonOutputDto(),
                this.profesor.ProfesorToProfesorOutputDto(),
                this.asignaturas.stream().map(Asignatura::AsignaturaToAsignaturaOutputDto).collect(Collectors.toSet())
        );
    }

    public StudentSimpleOutputDto studentToStudentSimpleOutputDto(){
        String profesor_id = this.profesor != null ? this.profesor.getId_profesor() : null;
        return new StudentSimpleOutputDto(
                this.id_student,
                this.num_hours_week,
                this.comments,
                this.branch
        );
    }

    public StudentsAsignaturas studentToStudentAsignaturas(){
        String profesor_id = this.profesor != null ? this.profesor.getId_profesor() : null;
        return new StudentsAsignaturas(
                this.id_student,
                this.asignaturas.stream().map(Asignatura::AsignaturaToAsignaturaOutputDto).collect(Collectors.toSet())
        );
    }
}

