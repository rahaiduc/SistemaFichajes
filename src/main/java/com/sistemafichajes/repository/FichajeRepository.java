package com.sistemafichajes.repository;

import com.sistemafichajes.domain.ClaveFichaje;
import com.sistemafichajes.domain.Empleado;
import com.sistemafichajes.domain.Fichaje;
import com.sistemafichajes.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FichajeRepository extends JpaRepository<Fichaje, String> {
    @Query("SELECT f FROM Fichaje f WHERE f.empleado.id_empleado = :idEmpleado ORDER BY f.timeEntry DESC")
    Optional<Fichaje> findTopByEmpleadoOrderByTimeEntryDesc(@Param("idEmpleado") String idEmpleado);

}
