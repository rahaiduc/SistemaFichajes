package com.sistemafichajes.controller.dto.outputs;

import com.sistemafichajes.domain.BranchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoOutputDto {
    private String id_empleado;
    private String id_persona;
    private BranchType branch;
    private Set<FichajeOutputDto> fichajes;

}
