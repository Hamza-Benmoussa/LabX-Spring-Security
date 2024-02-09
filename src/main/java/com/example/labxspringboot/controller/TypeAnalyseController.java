package com.example.labxspringboot.controller;

import com.example.labxspringboot.dto.TypeAnalyseDto;
import com.example.labxspringboot.entity.TypeAnalyse;
import com.example.labxspringboot.service.ITypeAnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping(value = "/api/typeanalyse" , produces = "application/json")
public class TypeAnalyseController {

    @Autowired
    private ITypeAnalyseService typeAnalyseService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<TypeAnalyseDto> saveTypeAnalyse(@RequestBody TypeAnalyseDto typeAnalyseDto) {
        TypeAnalyseDto savedTypeAnalyse = typeAnalyseService.saveTypeAnalyse(typeAnalyseDto);
        return new ResponseEntity<>(savedTypeAnalyse, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public List<TypeAnalyseDto> getAllTypeAnalyses() {
        return typeAnalyseService.getTypeAnalyses().stream()
                .map(typeAnalyse -> modelMapper.map(typeAnalyse, TypeAnalyseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<TypeAnalyseDto> getTypeAnalyseById(@PathVariable("id") Long typeAnalyseId) {
        TypeAnalyseDto typeAnalyseDto = typeAnalyseService.getTypeAnalyseById(typeAnalyseId);
        return ResponseEntity.ok(typeAnalyseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<TypeAnalyseDto> updateTypeAnalyse(@PathVariable("id") Long id,
                                                            @RequestBody TypeAnalyseDto typeAnalyseDto) {
        return ResponseEntity.ok(typeAnalyseService.updateTypeAnalyse(typeAnalyseDto, id));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RESPONSABLE_LABORATOIRE')")
    public ResponseEntity<String> deleteTypeAnalyse(@PathVariable("id") Long id) {
        typeAnalyseService.deleteTypeAnalyse(id);
        return ResponseEntity.ok("TypeAnalyse with id " +id+ "was deleted succes");
    }
}
