package com.example.labxspringboot.ControllersTesting;

import com.example.labxspringboot.controller.AnalyseController;
import com.example.labxspringboot.controller.UtilisateurController;
import com.example.labxspringboot.dto.*;
import com.example.labxspringboot.entity.*;
import com.example.labxspringboot.entity.enume.RoleUser;
import com.example.labxspringboot.service.IRapportResultatService;
import com.example.labxspringboot.service.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//import static org.h2.index.IndexCondition.get;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AnalyseController.class)
public class AnalyseControllerTest {
    @MockBean
    private AnalyseServiceImpl analyseService;

    @MockBean
    private EchantillonServiceImpl echantillonService;
    @MockBean
    PatientServiceImpl patientService;
    @MockBean
    UtilisateurServiceImpl utilisateurService;
    @MockBean
    TypeAnalyseServiceImpl typeAnalyseService;
    @MockBean
    IRapportResultatService iRapportResultatService;

    @Autowired
    MockMvc mockMvc;

    PatientDto patientDto;
    UtilisateurDto utilisateurDto;
    EchantillonDto echantillonDto;
    AnalyseDto analyseDto;
    TypeAnalyseDto typeAnalyseDto;

    ModelMapper modelMapper=new ModelMapper();

    Date date;
    SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd");
    @BeforeEach
    void setUp() throws ParseException {
        patientDto=new PatientDto();
        patientDto.setId(1L);
        patientDto.setNom("Piki");
        patientDto.setPrenom("piki");
        patientDto.setAdresse("182 red");
        patientDto.setNumeroTelephone("0147");
        patientDto.setSexe("Male");
        patientDto.setDateNaissance("2000-02-02");
        //when(i_patient.addPatient(patientDTO)).thenReturn(patientDTO);
        //PatientDTO patientDTO1=i_patient.addPatient(patientDTO);
        /***************************************************************/
        utilisateurDto=new UtilisateurDto();
        utilisateurDto.setId(1L);
        utilisateurDto.setEmail("piki@gmail.com");
        utilisateurDto.setMotDePasse("123");
        utilisateurDto.setRole(RoleUser.RESPONSABLE_LABORATOIRE);
        //when(i_utilisateur.addUser(utilisateurDTO)).thenReturn(utilisateurDTO);
        //UtilisateurDTO utilisateurDTO1=i_utilisateur.addUser(utilisateurDTO);
        /*************************************************************/
        echantillonDto=new EchantillonDto();
        echantillonDto.setId(1L);
        echantillonDto.setPatient(modelMapper.map(patientDto, Patient.class));
        echantillonDto.setUtilisateurPreleveur(modelMapper.map(utilisateurDto, Utilisateur.class));

        //when(i_echantillon.addEchantillon(echantillonDTO)).thenReturn(echantillonDTO);
        //EchantillonDTO echantillonDTO1=i_echantillon.addEchantillon(echantillonDTO);
        /****************************************************/
        analyseDto=new AnalyseDto();
        analyseDto.setId(1L);
        //analyseDto.setNomAnalyse(echantillonDto.getTypeAnalyse());
        analyseDto.setEchantillon(modelMapper.map(echantillonDto, Echantillon.class));
        //analyseDTO.setDateDebut("2000/06/06");
        //analyseDTO.setDateFin("2000/04/04"));
        //when(i_analyse.addAnalyse(analyseDTO)).thenReturn(analyseDTO);
    }
    @Test
    void getanalyse() throws Exception {
        Analyse analyse = new Analyse();
        analyse.setId(1L);
        // Set other properties as needed

        // Mock the service to return the Analyse object
        when(analyseService.getAnalyseById(1L)).thenReturn(analyseDto);

        // Perform the HTTP GET request
        MvcResult mvcResult = mockMvc.perform(get("/api/analyses/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // Assert that the response is not null
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }
    @Test
    void getanalyses() throws Exception {
        List<AnalyseDto> analyselist= Arrays.asList(analyseDto);
        when(analyseService.getAnalyses()).thenReturn(analyselist);

        MvcResult mvcResult= mockMvc.perform(get("/api/analyses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        //System.out.println(response);
        assertNotNull(response);
    }
    @Test
    void updateAnalyse() throws Exception {
        when(analyseService.getAnalyseById(1L)).thenReturn(analyseDto);
        analyseDto.setCommentaires("analyse a faire urgent");
        when(analyseService.updateAnalyse(analyseDto,1L)).thenReturn(analyseDto);
        MvcResult mvcResult = mockMvc.perform(put("/api/analyses/1") // Include the ID in the path
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(analyseDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void deleteAnalyse() throws Exception {
        doNothing().when(analyseService).deleteAnalyse(1L);

        ResultActions response = mockMvc.perform(delete("/api/analyses/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());

    }

}