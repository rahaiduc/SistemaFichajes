package com.sistemafichajes.controller.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsAsignaturas {
    String id_student;
    Set<AsignaturaOutputDto> asignaturas;
}
