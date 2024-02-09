package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByDeletedFalse();
    Optional<Patient> findByIdAndDeletedFalse(Long id);
}
