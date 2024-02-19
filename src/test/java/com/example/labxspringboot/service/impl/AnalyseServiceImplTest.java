package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.*;
import com.example.labxspringboot.entity.Echantillon;
import com.example.labxspringboot.entity.MaterielEchan;
import com.example.labxspringboot.entity.Patient;
import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.entity.enume.RoleUser;
import com.example.labxspringboot.entity.enume.StatusAnalyse;
import com.example.labxspringboot.service.*;
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
class AnalyseServiceImplTest {

    @Autowired
    private IEchantillonService iEchantillonService;

    @Autowired
    private IPatientService iPatientService;
    @Autowired
    private IMaterialEchanService iMaterialEchanService;
    @Autowired
    private IAnalyseService iAnalyseService;

    @Autowired
    private IUtilisateurService iUtilisateurService;

    @Autowired
    private ModelMapper modelMapper;

    private PatientDto patientDTO;
    private MaterielEchanDto materielEchanDto;
    private UtilisateurDto utilisateurDTO;
    private UtilisateurDto utilisateurDTO1;
    private EchantillonDto echantillonDTO;
    private AnalyseDto analyseDto;
    Date date;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");

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
        utilisateurDTO.setNom("mimi");
        utilisateurDTO.setEmail("h@gmail.com");
        utilisateurDTO.setMotDePasse("123");
        utilisateurDTO.setRole(RoleUser.TECHNICIEN);
        utilisateurDTO = iUtilisateurService.saveUtilisateur(utilisateurDTO);

        //Create utilisateur respo

        utilisateurDTO1 = new UtilisateurDto();
        utilisateurDTO1.setNom("kokl");
        utilisateurDTO1.setEmail("manini@gmail.com");
        utilisateurDTO1.setMotDePasse("123");
        utilisateurDTO1.setRole(RoleUser.RESPONSABLE_LABORATOIRE);
        utilisateurDTO1 = iUtilisateurService.saveUtilisateur(utilisateurDTO1);

        // Create EchantillonDTO
        echantillonDTO = new EchantillonDto();
        echantillonDTO.setPatient(modelMapper.map(patientDTO, Patient.class));
        echantillonDTO.setUtilisateurPreleveur(modelMapper.map(utilisateurDTO, Utilisateur.class));
        echantillonDTO.setDatePrelevement("2525");
        echantillonDTO.setNomAnalyse("momo");
        echantillonDTO = iEchantillonService.saveEchantillon(echantillonDTO);

        //Create Analyse

        analyseDto =new AnalyseDto();
        analyseDto.setCommentaires("ahah");
        analyseDto.setEchantillon(modelMapper.map(echantillonDTO , Echantillon.class));
        analyseDto.setStatusAnalyse(StatusAnalyse.EN_COURS_ANALYSE);
        analyseDto.setNom(echantillonDTO.getNomAnalyse());
        analyseDto.setDateFinAnalyse(String.valueOf(inputFormat.parse("2022-10-11")));
        analyseDto.setDateFinAnalyse(String.valueOf(inputFormat.parse("2022-10-15")));
        analyseDto.setUtilisateurTechnicien(modelMapper.map(utilisateurDTO1,Utilisateur.class));
        analyseDto =iAnalyseService.saveAnalyse(analyseDto);

    }

    @Test
    void saveAnalyse() {
        // Assertions
        assertNotNull(analyseDto.getId(), "Analyse ID should not be null");
        assertEquals("ahah", analyseDto.getCommentaires(), "Commentaires should match");
        // Add more assertions as needed
    }

    @Test
    void getAnalyses() {
        // Retrieve all analyses
        List<AnalyseDto> analyses = iAnalyseService.getAnalyses();
        assertNotNull(analyses, "List of analyses should not be null");
        assertFalse(analyses.isEmpty(), "List of analyses should not be empty");
    }

    @Test
    void getAnalyseById() {
        // Retrieve the previously saved analyse
        AnalyseDto retrievedAnalyse = iAnalyseService.getAnalyseById(analyseDto.getId());
        assertNotNull(retrievedAnalyse, "Analyse should be retrieved");
        assertEquals(analyseDto.getCommentaires(), retrievedAnalyse.getCommentaires(), "Commentaires should match");
    }

    @Test
    void updateAnalyse() {
        // Update the status of the analyse
        analyseDto.setStatusAnalyse(StatusAnalyse.TERMINE);
        AnalyseDto updatedAnalyse = iAnalyseService.updateAnalyse(analyseDto, analyseDto.getId());
        assertNotNull(updatedAnalyse, "Updated analyse should not be null");
        assertEquals(StatusAnalyse.TERMINE, updatedAnalyse.getStatusAnalyse(), "Status should be updated");
    }

    @Test
    void deleteAnalyse() {
        // Delete the analyse
        iAnalyseService.deleteAnalyse(analyseDto.getId());
        // Check if the analyse is marked as deleted
        AnalyseDto deletedAnalyse = iAnalyseService.getAnalyseById(analyseDto.getId());
        assertNull(deletedAnalyse, "Analyse should be deleted");
    }
}