package com.sistemafichajes.controller.dto.inputs;

import com.sistemafichajes.domain.BranchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoInputDto {
    private String id_empleado;
    private String id_persona;
    private String branch;
}
