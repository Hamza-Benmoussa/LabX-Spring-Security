package com.example.labxspringboot.repository;

import com.example.labxspringboot.entity.MaterielEchan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMaterialRepository extends JpaRepository<MaterielEchan,Long> {
    List<MaterielEchan> findByDeletedFalse();
    Optional<MaterielEchan> findByIdAndDeletedFalse(Long id);
}
