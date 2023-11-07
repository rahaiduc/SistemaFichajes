package com.sistemafichajes.domain;

import com.sistemafichajes.controller.dto.outputs.EmpleadoOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

@Entity
@Table(name="Empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(generator = "custom-string-id-generator")
    @GenericGenerator(name = "custom-string-id-generator", strategy = "com.sistemafichajes.domain.GeneradoresId.GeneradorIdPersona")
    private String id_empleado;

    @OneToOne
    @JoinColumn(name = "id_persona",nullable = false,unique = true)
    private Persona persona;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'FRONT'", nullable = false)
    private BranchType branch;

    @OneToMany(mappedBy = "id_empleado", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Fichaje> fichajes=new HashSet<>();

    public EmpleadoOutputDto EmpleadoToEmpleadoOutput(){
        return new EmpleadoOutputDto(
                this.id_empleado,
                this.persona.getId_persona(),
                this.branch
        );
    }

}
