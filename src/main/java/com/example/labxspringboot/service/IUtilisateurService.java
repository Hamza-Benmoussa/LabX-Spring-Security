package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.UtilisateurDto;
import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.exception.exept.UtilisateurFoundException;

import java.util.List;

public interface IUtilisateurService {

    UtilisateurDto saveUtilisateur(UtilisateurDto utilisateurDto);
    Utilisateur loadUserByEmail(String email);

    List<UtilisateurDto> getUtilisateurs();
    UtilisateurDto getUtilisateurById(Long id) throws UtilisateurFoundException;
    UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto,Long id);
    void deleteUtilisateur(Long id);
}
