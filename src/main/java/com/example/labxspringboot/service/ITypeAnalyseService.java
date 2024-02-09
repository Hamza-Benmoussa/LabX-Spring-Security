package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.AnalyseDto;
import com.example.labxspringboot.dto.TestAnalyseDto;
import com.example.labxspringboot.dto.TypeAnalyseDto;
import com.example.labxspringboot.entity.TypeAnalyse;

import java.util.List;

public interface ITypeAnalyseService {

    TypeAnalyseDto saveTypeAnalyse(TypeAnalyseDto typeAnalyseDto);
    List<TypeAnalyseDto> getTypeAnalyses();
    TypeAnalyseDto getTypeAnalyseById(Long id);
    TypeAnalyseDto updateTypeAnalyse(TypeAnalyseDto typeAnalyseDto, Long id);
    void deleteTypeAnalyse(Long id);

}
