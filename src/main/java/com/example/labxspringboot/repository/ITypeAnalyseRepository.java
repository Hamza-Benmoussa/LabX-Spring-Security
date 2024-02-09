package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.TestAnalyse;
import com.example.labxspringboot.entity.TypeAnalyse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITypeAnalyseRepository extends JpaRepository<TypeAnalyse ,Long> {

    List<TypeAnalyse> findByDeletedFalse();
    Optional<TypeAnalyse> findByIdAndDeletedFalse(Long id);
}
