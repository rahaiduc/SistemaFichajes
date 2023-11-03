package com.sistemafichajes.repository;

import com.sistemafichajes.domain.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, String> {
}
