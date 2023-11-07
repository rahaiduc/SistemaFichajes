package com.sistemafichajes.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Table(name="Fichaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fichaje {
    private String employeeId;
    private long entryTime;
    private long exitTime;

    Fichaje(String employeeId,long entryTime){
        this.employeeId=employeeId;
        this.entryTime=entryTime;
        this.exitTime=0;
    }
}
