package com.example.labxspringboot.controller;

import com.example.labxspringboot.dto.AnalyseDto;
import com.example.labxspringboot.dto.PlanificationDto;
import com.example.labxspringboot.service.IRapportResultatService;
import com.example.labxspringboot.service.impl.AnalyseServiceImpl;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/analyses" , produces = "application/json")
@Transactional
public class AnalyseController {

    @Autowired
    private AnalyseServiceImpl analyseService;
    @Autowired
    private IRapportResultatService iRapportResultatService;


    @GetMapping("/pdf/{id}")
    @PreAuthorize("hasAuthority('RRESPONSABLE_LABORATOIRE')")
    public ResponseEntity<Resource> getpdf(@PathVariable(value = "id") Long id_Analyse) throws JRException, FileNotFoundException, ParseException {
        byte[] reportContent=iRapportResultatService.exportRepport(id_Analyse);
        ByteArrayResource resource = new ByteArrayResource(reportContent);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("AnalyseRepprot.pdf")
                                .build().toString())
                .body(resource);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('TECHNICIEN','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<List<AnalyseDto>> getAllAnalyses() {
        return ResponseEntity.ok(analyseService.getAnalyses());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TECHNICIEN','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<AnalyseDto> getAnalyseById(@PathVariable("id") Long analyseId) {
    AnalyseDto analyseDto = analyseService.getAnalyseById(analyseId);
    return ResponseEntity.ok(analyseDto);
 }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('TECHNICIEN','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<AnalyseDto> updateAnalyse(@PathVariable("id") Long id, @RequestBody AnalyseDto analyseDto) {
    return ResponseEntity.ok(analyseService.updateAnalyse(analyseDto,id));
 }

    @PostMapping("/planification")
    @PreAuthorize("hasAnyAuthority('PRELEVEUR','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<PlanificationDto> affecterAnalyse(@RequestBody PlanificationDto planificationDto){
        planificationDto = analyseService.planifierAnalyse(planificationDto);
        return new ResponseEntity<>(planificationDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TECHNICIEN','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<String> deleteAnalyseById(@PathVariable("id") Long id) {
    analyseService.deleteAnalyse(id);
    return ResponseEntity.ok("Analyse with : "+id+"has benn deleted succes");
 }
}