package com.sistemafichajes.controller.dto.outputs;

import com.sistemafichajes.domain.BranchType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSimpleOutputDto {
    String id_student;
    int num_hours_week;
    String comments;
    BranchType branch;
}