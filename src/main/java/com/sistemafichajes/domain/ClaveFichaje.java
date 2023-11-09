package com.sistemafichajes.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaveFichaje implements Serializable {
    private String id_empleado;
    private long timeEntry;
    private long timeExit;

    ClaveFichaje(String id_empleado){
        this.id_empleado=id_empleado;
        this.timeEntry=new Date().getTime();
        this.timeExit=0;
    }

}
