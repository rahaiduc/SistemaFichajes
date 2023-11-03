package com.sistemafichajes.controller.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEstudianteOutputDto extends PersonOutputDto{
    private StudentFullOutputDto studentFullOutputDto;
}
