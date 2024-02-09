package com.example.labxspringboot.controller;

import com.example.labxspringboot.dto.PatientDto;
import com.example.labxspringboot.entity.Patient;
import com.example.labxspringboot.service.IPatientService;
import com.example.labxspringboot.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/patients",produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    @Autowired
    private PatientServiceImpl patientService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('RESPONSABLE_LABORATOIRE','PRELEVEUR')")
    public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDto){
        PatientDto savePatientDto = patientService.savePatient(patientDto);
        return new  ResponseEntity<>(savePatientDto, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('RESPONSABLE_LABORATOIRE','PRELEVEUR')")
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getPatients());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('RESPONSABLE_LABORATOIRE','PRELEVEUR')")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable ("id") Long patientId){
        PatientDto patientDto = patientService.getPatientById(patientId);
        return ResponseEntity.ok(patientDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('RESPONSABLE_LABORATOIRE','PRELEVEUR')")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable ("id") Long id, @RequestBody PatientDto patientDto){
        return ResponseEntity.ok(patientService.updatePatient(patientDto,id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('RESPONSABLE_LABORATOIRE','PRELEVEUR')")
    public ResponseEntity<String> deletePatient(@PathVariable ("id") Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient with id : " + id + "was deleted");
    }
}
