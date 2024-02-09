package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.UtilisateurDto;
import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.entity.enume.RoleUser;
import com.example.labxspringboot.exception.exept.UtilisateurFoundException;
import com.example.labxspringboot.repository.IUtilisateurRepository;
import com.example.labxspringboot.service.IUtilisateurService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UtilisateurServiceImplTest {

    @Autowired
    private IUtilisateurService iUtilisateurService;

    @Autowired
    private ModelMapper modelMapper;

    private UtilisateurDto utilisateurDto;

    @BeforeEach
    void setUp() {
        // Create a sample UtilisateurDto
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNom("lhytman");
        utilisateurDto.setEmail("hamza@gmail.com");
        utilisateurDto.setMotDePasse("testPassword");
        utilisateurDto.setRole(RoleUser.TECHNICIEN);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveUtilisateur() throws UtilisateurFoundException {
        UtilisateurDto savedUtilisateurDto = iUtilisateurService.saveUtilisateur(utilisateurDto);
        assertNotNull(savedUtilisateurDto, "Utilisateur not inserted");

        // Retrieve the saved Utilisateur and assert its properties
        UtilisateurDto retrievedUtilisateurDto = iUtilisateurService.getUtilisateurById(savedUtilisateurDto.getId());
        assertNotNull(retrievedUtilisateurDto, "Utilisateur not found in the database");
        assertEquals("****", retrievedUtilisateurDto.getMotDePasse(), "Password should be masked");
        // Add more assertions as needed
    }

    @Test
    void getUtilisateurs() {
        List<UtilisateurDto> utilisateurDtos = iUtilisateurService.getUtilisateurs();
        assertNotNull(utilisateurDtos, "List is empty");
    }

    @Test
    void getUtilisateurById() throws UtilisateurFoundException {
        UtilisateurDto savedUtilisateurDto = iUtilisateurService.saveUtilisateur(utilisateurDto);
        UtilisateurDto retrievedUtilisateurDto = iUtilisateurService.getUtilisateurById(savedUtilisateurDto.getId());
        assertNotNull(retrievedUtilisateurDto, "Utilisateur not found");
    }

    @Test
    void updateUtilisateur() {
        UtilisateurDto savedUtilisateurDto = iUtilisateurService.saveUtilisateur(utilisateurDto);
        savedUtilisateurDto.setEmail("updatedUser@u.com");
        UtilisateurDto updatedUtilisateurDto = iUtilisateurService.updateUtilisateur(savedUtilisateurDto, savedUtilisateurDto.getId());
        assertNotNull(updatedUtilisateurDto, "Utilisateur not updated");
        assertEquals("****", updatedUtilisateurDto.getMotDePasse(), "Password should be masked");
        assertEquals("updatedUser@u.com", updatedUtilisateurDto.getEmail(), "Username should be updated");
    }

    @Test
    void deleteUtilisateur() throws UtilisateurFoundException {
        // Save a Utilisateur to be deleted
        UtilisateurDto savedUtilisateurDto = iUtilisateurService.saveUtilisateur(utilisateurDto);
        Long utilisateurId = savedUtilisateurDto.getId();

        // Verify that the Utilisateur is initially present
        assertNotNull(iUtilisateurService.getUtilisateurById(utilisateurId), "Utilisateur not found before deletion");

        // Delete the Utilisateur
        iUtilisateurService.deleteUtilisateur(utilisateurId);

        }


}
