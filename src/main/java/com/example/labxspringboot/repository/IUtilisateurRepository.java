package com.example.labxspringboot.repository;

import com.example.labxspringboot.dto.UtilisateurDto;
import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    List<Utilisateur> findByDeletedFalse();
    Optional<Utilisateur> findByIdAndDeletedFalse(Long id);
    Utilisateur findByEmailAndDeletedFalse(String email);
}
