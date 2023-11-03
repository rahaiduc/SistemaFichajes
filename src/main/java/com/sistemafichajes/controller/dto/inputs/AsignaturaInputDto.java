package com.sistemafichajes.controller.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDto {
    private String id_asignatura;
    private String asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;
}
