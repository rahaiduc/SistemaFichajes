package com.sistemafichajes.controller.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichajeOutputDto {
    private String id_fichaje;
    private String id_empleado;
    private Date timeEntry;
    private Date timeExit;
}
