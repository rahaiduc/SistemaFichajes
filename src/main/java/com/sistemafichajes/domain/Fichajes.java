package com.sistemafichajes.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fichajes {
    private String employeeId;
    private long entryTime;
    private long exitTime;
}
