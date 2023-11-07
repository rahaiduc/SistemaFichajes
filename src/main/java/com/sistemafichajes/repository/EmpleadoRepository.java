package com.sistemafichajes.repository;

import com.sistemafichajes.domain.Asignatura;
import com.sistemafichajes.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

}
