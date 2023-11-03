package com.sistemafichajes.repository;

import com.sistemafichajes.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {
}
