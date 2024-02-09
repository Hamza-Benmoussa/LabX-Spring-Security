package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.EchantillonDto;

import java.util.List;

public interface IEchantillonService {
    EchantillonDto saveEchantillon(EchantillonDto echantillonDto);
    List<EchantillonDto> getEchantillons();
    EchantillonDto getEchantillonById(Long id);
    EchantillonDto updateEchantillon(EchantillonDto echantillonDto, Long id);
    void deleteEchantillon(Long id);
}
