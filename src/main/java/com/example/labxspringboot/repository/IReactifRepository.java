package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Reactif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReactifRepository extends JpaRepository<Reactif , Long> {

    List<Reactif> findByDeletedFalse();
    Optional<Reactif> findByIdAndDeletedFalse(Long id);

}
