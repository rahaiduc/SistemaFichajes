package com.sistemafichajes.domain;

import com.sistemafichajes.controller.dto.outputs.EmpleadoOutputDto;
import com.sistemafichajes.controller.dto.outputs.FichajeOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name="Fichaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fichaje {
    @Id
    @GeneratedValue(generator = "custom-string-id-generator")
    @GenericGenerator(name = "custom-string-id-generator", strategy = "com.sistemafichajes.domain.GeneradoresId.GeneradorIdPersona")
    private String id_fichaje;

    @ManyToOne
    private Empleado empleado;


    private long timeEntry;


    private long timeExit;

    public FichajeOutputDto FichajeToFichajeOutput(){
        return new FichajeOutputDto(
                this.id_fichaje,
                this.empleado.getId_empleado(),
                new Date(timeEntry),
                new Date(timeExit)
        );
    }

}
