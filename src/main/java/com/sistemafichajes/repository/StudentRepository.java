package com.sistemafichajes.repository;

import com.sistemafichajes.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
