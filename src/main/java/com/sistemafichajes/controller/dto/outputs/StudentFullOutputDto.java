package com.sistemafichajes.controller.dto.outputs;

import com.sistemafichajes.domain.BranchType;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFullOutputDto {
    String id_student;
    int num_hours_week;
    String comments;
    BranchType branch;
    PersonOutputDto personOutputDto;
    ProfesorOutputDto profesorOutputDto;
    Set<AsignaturaOutputDto> asignaturas;
}
