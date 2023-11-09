package com.sistemafichajes.controller.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichajeInputDto {
    private String id_fichaje;
    private String id_empleado;
    private long timeEntry;
    private long timeExit;
}
