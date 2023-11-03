package com.sistemafichajes.controller.dto.outputs;

import com.sistemafichajes.domain.BranchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDto {
    private String id_profesor;
    private String id_persona;
    private String comments;
    private BranchType branch;
    private Set<StudentsAsignaturas> students;
}
