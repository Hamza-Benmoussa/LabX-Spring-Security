package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Analyse;
import com.example.labxspringboot.entity.Echantillon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEchantillonRepository extends JpaRepository<Echantillon ,Long> {
    Optional<Echantillon> findByIdAndDeletedFalse(Long id);
    List<Echantillon> findByDeletedFalse();
}
