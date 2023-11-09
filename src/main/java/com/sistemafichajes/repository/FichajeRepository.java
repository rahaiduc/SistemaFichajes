package com.sistemafichajes.repository;

import com.sistemafichajes.domain.ClaveFichaje;
import com.sistemafichajes.domain.Fichaje;
import com.sistemafichajes.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichajeRepository extends JpaRepository<Fichaje, String> {
}
