package com.sistemafichajes.controller.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {
    private String id_profesor;
    private String id_persona;
    private String comments;
    private String branch;
}