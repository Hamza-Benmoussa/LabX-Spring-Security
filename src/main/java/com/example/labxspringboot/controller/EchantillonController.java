package com.example.labxspringboot.controller;

import com.example.labxspringboot.dto.AnalyseDto;
import com.example.labxspringboot.dto.EchantillonDto;
import com.example.labxspringboot.dto.MaterielEchanDto;
import com.example.labxspringboot.entity.*;
import com.example.labxspringboot.exception.exept.UtilisateurFoundException;
import com.example.labxspringboot.service.IEchantillonMaterialService;
import com.example.labxspringboot.service.IMaterialEchanService;
import com.example.labxspringboot.service.IUtilisateurService;
import com.example.labxspringboot.service.impl.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/echantillons" ,produces = "application/json")
@Transactional
@CrossOrigin("*")
@Slf4j
public class EchantillonController {

    @Autowired
    private EchantillonServiceImpl echantillonService;
    @Autowired
    private MaterialEchanServiceImpl materialEchanService;
    @Autowired
    private EchantillonMaterialServiceImpl iEchantillonMaterialService;
    @Autowired
    private UtilisateurServiceImpl utilisateurService;
    @Autowired
    private AnalyseServiceImpl analyseService;

    @Autowired
    private ModelMapper modelMapper;
    @Transactional
    @PostMapping
    @PreAuthorize("hasAnyAuthority('PRELEVEUR','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<EchantillonDto> saveEchantillon(@RequestBody EchantillonDto echantillonDto) throws UtilisateurFoundException {
        log.info("EchantillonDto {}",echantillonDto);
        Utilisateur utilisateur=modelMapper.map(utilisateurService.getUtilisateurById(echantillonDto.getUtilisateurPreleveur().getId()),Utilisateur.class);
        log.info("UtilisateurDto {}",utilisateur);
        echantillonDto.setUtilisateurPreleveur(utilisateur);
        EchantillonDto saveEchantillonDto = echantillonService.saveEchantillon(echantillonDto);
        EchantillonDto echantillonDto1 = saveEchantillonDto;
        if (echantillonDto.getEchantillonMaterials() != null) {
            for (EchantillonMaterial echantillonMaterial : echantillonDto.getEchantillonMaterials()) {
                MaterielEchan materielEchan = modelMapper.map(materialEchanService.getMaterialEchanById(echantillonMaterial.getMaterielEchan().getId()), MaterielEchan.class);
                log.info("MaterialEchan {}",materielEchan);
                echantillonMaterial.setEchantillon(modelMapper.map(echantillonDto1, Echantillon.class));
                echantillonMaterial.setMaterielEchan(materielEchan);
                iEchantillonMaterialService.addEchantillon(echantillonMaterial);

                materielEchan.setQuantiteStockEhcna(materielEchan.getQuantiteStockEhcna() - echantillonMaterial.getQuantity());
                materialEchanService.updateMaterialEchan(modelMapper.map(materielEchan, MaterielEchanDto.class), materielEchan.getId());
                log.info("MaterialEchan22 {}",materielEchan);
            }
        }
        return new ResponseEntity<>(saveEchantillonDto , HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('PRELEVEUR','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<List<EchantillonDto>> getAllEchantillons() {
        return ResponseEntity.ok(echantillonService.getEchantillons());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRELEVEUR','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<EchantillonDto> getEchantillonById(@PathVariable("id") Long echantillonId) {
        EchantillonDto echantillonDto = echantillonService.getEchantillonById(echantillonId);
        return ResponseEntity.ok(echantillonDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRELEVEUR','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<EchantillonDto> updateEchantillon(@PathVariable("id") Long id, @RequestBody EchantillonDto echantillonDto) {
       return ResponseEntity.ok(echantillonService.updateEchantillon(echantillonDto ,id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRELEVEUR','RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<String> deleteEchantillonById(@PathVariable("id") Long id) {
        echantillonService.deleteEchantillon(id);
        return ResponseEntity.ok("Echantillon with id : " + id + "was deleted");
    }
}
