package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.MaterielEchanDto;
import com.example.labxspringboot.dto.PatientDto;
import com.example.labxspringboot.entity.Patient;

import java.util.List;

public interface IPatientService {

    PatientDto savePatient(PatientDto patientDto);

    List<PatientDto> getPatients();

    PatientDto getPatientById(Long id);

    PatientDto updatePatient(PatientDto patientDto , Long id);

    void deletePatient(Long id);
}
