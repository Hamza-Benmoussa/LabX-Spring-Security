package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.AnalyseDto;
import com.example.labxspringboot.entity.Echantillon;
import com.example.labxspringboot.dto.PlanificationDto;

import java.util.List;

public interface IAnalyseService {
    AnalyseDto saveAnalyse(AnalyseDto analyseDto);
    List<AnalyseDto> getAnalyses();
    AnalyseDto getAnalyseById(Long id);
    AnalyseDto updateAnalyse(AnalyseDto analyseDto, Long id);
    AnalyseDto createAnalyseForEchantillon(Echantillon echantillon);

    void deleteAnalyse(Long id);
    public PlanificationDto planifierAnalyse(PlanificationDto planificationDTO);

    List<Object[]> printResultAnalyse(Long id);

}
