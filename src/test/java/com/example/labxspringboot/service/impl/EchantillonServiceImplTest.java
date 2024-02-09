package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.EchantillonDto;
import com.example.labxspringboot.dto.MaterielEchanDto;
import com.example.labxspringboot.dto.PatientDto;
import com.example.labxspringboot.dto.UtilisateurDto;
import com.example.labxspringboot.entity.MaterielEchan;
import com.example.labxspringboot.entity.Patient;
import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.entity.enume.RoleUser;
import com.example.labxspringboot.service.IEchantillonService;
import com.example.labxspringboot.service.IMaterialEchanService;
import com.example.labxspringboot.service.IPatientService;
import com.example.labxspringboot.service.IUtilisateurService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EchantillonServiceImplTest {

    @Autowired
    private IEchantillonService iEchantillonService;

    @Autowired
    private IPatientService iPatientService;
    @Autowired
    private IMaterialEchanService iMaterialEchanService;

    @Autowired
    private IUtilisateurService iUtilisateurService;

    @Autowired
    private ModelMapper modelMapper;

    private PatientDto patientDTO;
    private MaterielEchanDto materielEchanDto;
    private UtilisateurDto utilisateurDTO;
    private EchantillonDto echantillonDTO;

    @BeforeEach
    void setUp() throws ParseException {
        // Create a sample Patient
        patientDTO = new PatientDto();
        patientDTO.setNom("mohammed");
        patientDTO.setPrenom("prenom mohammed");
        patientDTO.setAdresse("qwerty");
        patientDTO.setNumeroTelephone("02125232525");
        patientDTO.setSexe("Male");
        patientDTO.setDateNaissance("2000");
        patientDTO = iPatientService.savePatient(patientDTO);

        materielEchanDto = new MaterielEchanDto();
        materielEchanDto.setNomechan("lhyt man");
        materielEchanDto.setFournisseurNom("lol");
        materielEchanDto.setDateExpirationEchan("2525");
        materielEchanDto.setQuantiteStockEhcna(10);
        materielEchanDto = iMaterialEchanService.saveMaterialEchan(materielEchanDto);

        // Create a sample Utilisateur
        utilisateurDTO = new UtilisateurDto();
        utilisateurDTO.setNom("kama");
        utilisateurDTO.setEmail("hamza@gmail.com");
        utilisateurDTO.setMotDePasse("123");
        utilisateurDTO.setRole(RoleUser.TECHNICIEN);
        utilisateurDTO = iUtilisateurService.saveUtilisateur(utilisateurDTO);

        // Create a sample EchantillonDTO
        echantillonDTO = new EchantillonDto();
        echantillonDTO.setPatient(modelMapper.map(patientDTO, Patient.class));
        echantillonDTO.setUtilisateurPreleveur(modelMapper.map(utilisateurDTO, Utilisateur.class));
        echantillonDTO.setDatePrelevement("2525");
        echantillonDTO.setNomAnalyse("biochimie");
        echantillonDTO = iEchantillonService.saveEchantillon(echantillonDTO);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addEchantillon() {
        EchantillonDto savedEchantillonDTO = iEchantillonService.saveEchantillon(echantillonDTO);
        assertNotNull(savedEchantillonDTO, "Echantillon not inserted");

        // Retrieve the saved Echantillon and assert its properties
        EchantillonDto retrievedEchantillonDTO = iEchantillonService.getEchantillonById(savedEchantillonDTO.getId());
        assertNotNull(retrievedEchantillonDTO, "Echantillon not found in the database");
        // Add more assertions as needed
    }

    @Test
    void getAllEchantillons() {
        List<EchantillonDto> echantillonDTOS = iEchantillonService.getEchantillons();
        assertNotNull(echantillonDTOS, "List is empty");
    }

    @Test
    void getEchantillonById() {
        EchantillonDto savedEchantillonDTO = iEchantillonService.saveEchantillon(echantillonDTO);
        EchantillonDto retrievedEchantillonDTO = iEchantillonService.getEchantillonById(savedEchantillonDTO.getId());
        assertNotNull(retrievedEchantillonDTO, "Echantillon not found");
    }

    @Test
    void updateEchantillon() {
        // Save the EchantillonDto
        EchantillonDto savedEchantillonDTO = iEchantillonService.saveEchantillon(echantillonDTO);

        // Modify the datePrelevement field
        savedEchantillonDTO.setDatePrelevement("2024-01-25"); // Change the date as needed

        // Update the EchantillonDto
        EchantillonDto updatedEchantillonDTO = iEchantillonService.updateEchantillon(savedEchantillonDTO, savedEchantillonDTO.getId());

        // Assert that the update was successful
        assertNotNull(updatedEchantillonDTO, "Echantillon not updated");

        // Additional assertions if needed
        assertEquals("2024-01-25", updatedEchantillonDTO.getDatePrelevement(), "DatePrelevement should be updated");
    }

    @Test
    void delEchantillon() {
        // Save a new Echantillon
        EchantillonDto savedEchantillonDTO = iEchantillonService.saveEchantillon(echantillonDTO);
        assertNotNull(savedEchantillonDTO, "Echantillon not inserted");

        // Delete the saved Echantillon
        iEchantillonService.deleteEchantillon(savedEchantillonDTO.getId());

        // Try to retrieve the deleted Echantillon
        EchantillonDto deletedEchantillonDTO = iEchantillonService.getEchantillonById(savedEchantillonDTO.getId());

        assertNull(deletedEchantillonDTO, "Echantillon should be deleted");
    }
}
