package com.example.labxspringboot.controller;

import com.example.labxspringboot.dto.UtilisateurDto;
import com.example.labxspringboot.exception.exept.UtilisateurFoundException;
import com.example.labxspringboot.service.impl.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/utilisateurs" , produces = "application/json")
public class UtilisateurController {
    @Autowired
    private UtilisateurServiceImpl utilisateurService;
    @PostMapping
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<UtilisateurDto>saveUtilisateur(@RequestBody @Valid UtilisateurDto utilisateurDto){
        UtilisateurDto saveUtilisateurDto = utilisateurService.saveUtilisateur(utilisateurDto);
        return  new ResponseEntity<>(saveUtilisateurDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable("id")  Long utilisateurId) throws UtilisateurFoundException {
        UtilisateurDto utilisateurDto = utilisateurService.getUtilisateurById(utilisateurId);
        return ResponseEntity.ok(utilisateurDto);
    }
    @GetMapping
//    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getUtilisateurs());
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable("id") Long id, @RequestBody @Valid UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(utilisateurService.updateUtilisateur(utilisateurDto ,id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<String> deleteUtilisateurById(@PathVariable("id") Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("Utilisateur with id : " + id + "was deleted");
    }

}
