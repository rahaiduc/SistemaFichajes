package com.sistemafichajes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private Date timestamp;
    private int httpCode;
    private String mensaje;
}
