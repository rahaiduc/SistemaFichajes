package com.sistemafichajes.controller.dto.outputs;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaOutputDto {
    private String id_asignatura;
    private String asignatura;
    private String comments;
    private Date initial_date;
    private Date finish_date;
}
