package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.MaterielEchanDto;

import java.util.List;

public interface IMaterialEchanService {

    MaterielEchanDto saveMaterialEchan(MaterielEchanDto materielEchanDto);

    List<MaterielEchanDto> getMaterialEchans();

    MaterielEchanDto getMaterialEchanById(Long id);

    MaterielEchanDto updateMaterialEchan(MaterielEchanDto materielEchanDto, Long id);

    void deleteMaterialEchan(Long id);
}
