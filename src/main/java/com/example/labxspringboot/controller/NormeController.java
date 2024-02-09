package com.example.labxspringboot.controller;

import com.example.labxspringboot.dto.NormeDto;
import com.example.labxspringboot.service.impl.NormeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/normes",produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class NormeController {

    @Autowired
    private NormeServiceImpl normeService;

    @PostMapping
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<NormeDto> saveNorme(@RequestBody NormeDto normeDto) {
        NormeDto savedNormeDto = normeService.saveNorme(normeDto);
        return new ResponseEntity<>(savedNormeDto, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<List<NormeDto>> getAllNormes() {
        return ResponseEntity.ok(normeService.getNormes());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<NormeDto> getNormeById(@PathVariable("id") Long normeId) {
        NormeDto normeDto = normeService.getNormeById(normeId);
        return ResponseEntity.ok(normeDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<NormeDto> updateNorme(@PathVariable("id") Long id, @RequestBody NormeDto normeDto) {
        return ResponseEntity.ok(normeService.updateNorme(normeDto, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<String> deleteNormeById(@PathVariable("id") Long id) {
        normeService.deleteNorme(id);
        return ResponseEntity.ok("Norme with id : " + id + " was deleted");
    }
}
