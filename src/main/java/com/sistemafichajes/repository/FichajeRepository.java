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
    @Query("SELECT f FROM Fichaje f " +
            "WHERE f.empleado.id_empleado = :idEmpleado " +
            "AND f.timeEntry = (SELECT MAX(f2.timeEntry) FROM Fichaje f2 WHERE f2.empleado.id_empleado = :idEmpleado)")
    Optional<Fichaje> findTopByEmpleadoOrderByTimeEntryDesc(@Param("idEmpleado") String idEmpleado);

}
