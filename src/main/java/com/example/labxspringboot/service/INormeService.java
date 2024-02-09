package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.NormeDto;

import java.util.List;

public interface INormeService {
    NormeDto saveNorme(NormeDto normeDto);

    List<NormeDto> getNormes();

    NormeDto getNormeById(Long id);

    NormeDto updateNorme(NormeDto normeDto, Long id);

    void deleteNorme(Long id);
}
