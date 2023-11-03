package com.sistemafichajes.controller.dto.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    private String id_student;
    private String id_persona;
    private String id_profesor;
    private int num_hours_week;
    private String comments;
    private String branch;
}
