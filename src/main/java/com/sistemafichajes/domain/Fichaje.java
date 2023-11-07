package com.sistemafichajes.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Fichaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fichaje {
    @EmbeddedId
    private ClaveFichaje clave;

    Fichaje(String employeeId){
        this.clave=new ClaveFichaje(employeeId);
    }
}
