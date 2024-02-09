package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Norme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INormeRepository extends JpaRepository<Norme,Long> {
    List<Norme> findByDeletedFalse();
    Optional<Norme> findByIdAndDeletedFalse(Long id);

}
