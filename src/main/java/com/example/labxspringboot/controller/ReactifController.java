package com.example.labxspringboot.controller;

import com.example.labxspringboot.dto.ReactifDto;
import com.example.labxspringboot.entity.Reactif;
import com.example.labxspringboot.service.impl.ReactifServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/reactifs" , produces = "application/json")
public class ReactifController {

    @Autowired
    private ReactifServiceImpl reactifService;

    @PostMapping
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<ReactifDto> saveReactif(@RequestBody ReactifDto reactifDto) {
        ReactifDto savedReactifDto = reactifService.saveReactif(reactifDto);
        return new ResponseEntity<>(savedReactifDto, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<List<ReactifDto>> getAllReactifs() {
        return ResponseEntity.ok(reactifService.getReactifs());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<ReactifDto> getReactifById(@PathVariable("id") Long reactifId) {
        ReactifDto reactifDto = reactifService.getReactifById(reactifId);
        return ResponseEntity.ok(reactifDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<ReactifDto> updateReactif(@PathVariable("id") Long id, @RequestBody ReactifDto reactifDto) {
        return ResponseEntity.ok(reactifService.updateReactif(reactifDto,id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<String> deleteNorme(@PathVariable("id") Long id) {
        reactifService.deleteReactif(id);
        return ResponseEntity.ok("Reactif with id : " + id + "was deleted");
    }
}
