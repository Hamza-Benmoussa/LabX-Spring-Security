package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.NormeDto;
import com.example.labxspringboot.dto.PatientDto;
import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Patient;
import com.example.labxspringboot.repository.INormeRepository;
import com.example.labxspringboot.repository.IPatientRepository;
import com.example.labxspringboot.service.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
    private IPatientRepository patientRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PatientDto savePatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        Patient savePatient = patientRepository.save(patient);
        return modelMapper.map(savePatient, PatientDto.class);
    }

    @Override
    public List<PatientDto> getPatients() {
        List<Patient> patients = patientRepository.findByDeletedFalse();
        return patients.stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(Long id) {
        return patientRepository.findByIdAndDeletedFalse(id)
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .orElse(null);
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto, Long id) {
        Patient existingPatient = patientRepository.findByIdAndDeletedFalse(id).orElse(null);
        existingPatient.setNom(patientDto.getNom());
        existingPatient.setPrenom(patientDto.getPrenom());
        existingPatient.setDateNaissance(patientDto.getDateNaissance());
        existingPatient.setSexe(patientDto.getSexe());
        existingPatient.setAdresse(patientDto.getAdresse());
        existingPatient.setNumeroTelephone(patientDto.getNumeroTelephone());
        Patient updatePatient = patientRepository.save(existingPatient);

        updatePatient.setId(id);

        return modelMapper.map(updatePatient, PatientDto.class);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (patient != null) {
            patient.setDeleted(true);
            patientRepository.save(patient);
        }
    }
}
