package com.sistemafichajes.controller.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {
    private String id_persona;
    private String usuario;
    private String name;
    private String surname;
    private String company_email;
    private boolean active;
}
