package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.EchantillonDto;
import com.example.labxspringboot.dto.PatientDto;
import com.example.labxspringboot.service.IPatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.h2.index.Cursor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceImplTest {

    @Autowired
    private IPatientService iPatientService;

    PatientDto patientDto;

    ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp(){
        patientDto = new PatientDto();
        patientDto.setId(1L);
        patientDto.setNom("mamamamama");
        patientDto.setPrenom("m3ayti");
        patientDto.setSexe("homme");
        patientDto.setAdresse("tacharouk");
        patientDto.setNumeroTelephone("789654123");
        patientDto.setDateNaissance("1999");
    }

    @Test
    void savePatient() {
        PatientDto patientDto1 = iPatientService.savePatient(patientDto);
        assertNotNull(patientDto1,"Patient Not found");
    }

    @Test
    void getPatients() {
        PatientDto patientDto1 = iPatientService.savePatient(patientDto);
        List<PatientDto> patientDtos = iPatientService.getPatients();
        assertNotNull(patientDtos,"list is empty");
    }

    @Test
    void getPatientById() {
        PatientDto patientDto1 =iPatientService.savePatient(patientDto);
        assertNotNull(patientDto1,"patirnt not found ");
    }

    @Test
    void updatePatient() {
        PatientDto patientDto1 = iPatientService.savePatient(patientDto);
        String name = patientDto1.getNom();
        patientDto1.setNom("MIMO");
        patientDto1 = iPatientService.updatePatient(patientDto1, patientDto.getId());
        assertNotNull(name, patientDto1.getNom());
    }

    @Test
    void deletePatient() {
        PatientDto patientDto1 = iPatientService.savePatient(patientDto);

        // Delete the saved patient
        iPatientService.deletePatient(patientDto1.getId());

        // Verify that the patient is deleted
        assertNull(iPatientService.getPatientById(patientDto1.getId()), "Patient should be deleted");
    }
}