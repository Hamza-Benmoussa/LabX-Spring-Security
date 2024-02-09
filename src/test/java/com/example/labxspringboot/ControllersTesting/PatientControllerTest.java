package com.example.labxspringboot.ControllersTesting;

import com.example.labxspringboot.controller.PatientController;
import com.example.labxspringboot.controller.UtilisateurController;
import com.example.labxspringboot.dto.PatientDto;
import com.example.labxspringboot.entity.Patient;

import com.example.labxspringboot.service.impl.PatientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientService;

    @Autowired
    private ObjectMapper objectMapper;
    private PatientDto patientDto;
    private Patient patient;

    @BeforeEach
    public void init() {
        patientDto = new PatientDto(); // Initialize utilisateurDto
        patientDto.setNom("Raichu");
        patientDto.setPrenom("Pockemon");
        patientDto.setSexe("Homme");
        patientDto.setAdresse("red 126");
        patientDto.setNumeroTelephone("0147852");
        patientDto.setDateNaissance("12/12/2012");


    }
    @Test
    public void createPatientTest() throws Exception {
        given(patientService.savePatient(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing HTTP POST request
        ResultActions response = mockMvc.perform(post("/api/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientDto))); // Setting JSON content

        // Verifying HTTP status and JSON content
        response.andExpect(status().isCreated())
                .andDo(print()).andReturn();
    }
    @Test
    public void getPatientTest() throws Exception {
        Long patientId = 1L;
        when(patientService.getPatientById(patientId)).thenReturn(patientDto);

        ResultActions response = mockMvc.perform(get("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientDto)));

        response.andExpect(status().isOk())
                .andDo(print()).andReturn();}
    @Test
    public void getAllPatients() throws Exception {

        PatientDto patient1 = new PatientDto();
        patient1.setId(1L);
        patient1.setNom("John");
        patient1.setPrenom("Doe");
        patient1.setSexe("Male");

        List<PatientDto> patientList = Arrays.asList(patient1);

        when(patientService.getPatients()).thenReturn(patientList);

        mockMvc.perform(get("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void UpdatePatientTest() throws Exception {
        Long patientId = 1L;
        when(patientService.updatePatient(patientDto, patientId)).thenReturn(patientDto);

        ResultActions response = mockMvc.perform(put("/api/patients/1/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientDto)));

        response.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void DeletePatientTest() throws Exception {
        Long patientId = 1L;
        doNothing().when(patientService).deletePatient(1L);

        ResultActions response = mockMvc.perform(delete("/api/patients/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }
}