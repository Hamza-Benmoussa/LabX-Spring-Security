package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.AnalyseDto;
import com.example.labxspringboot.entity.Analyse;
import com.example.labxspringboot.entity.Echantillon;
import com.example.labxspringboot.entity.Planification;
import com.example.labxspringboot.dto.PlanificationDto;
import com.example.labxspringboot.repository.IAnalyseRepository;
import com.example.labxspringboot.repository.IPlanificationRepository;
import com.example.labxspringboot.service.IAnalyseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AnalyseServiceImpl implements IAnalyseService {

    @Autowired
    private IAnalyseRepository iAnalyseRepository;

    @Autowired
    private IPlanificationRepository iPlanificationRepository;

    private final ModelMapper modelMapper;

    @Override
    public AnalyseDto saveAnalyse(AnalyseDto analyseDto) {
        Analyse analyse = modelMapper.map(analyseDto ,Analyse.class);
        Analyse analyseSave = iAnalyseRepository.save(analyse);
        return modelMapper.map(analyseSave,AnalyseDto.class);
    }

    @Override
    public List<AnalyseDto> getAnalyses() {
        List<Analyse> analyses = iAnalyseRepository.findByDeletedFalse();
        return analyses.stream().map(analyse -> modelMapper.map(analyse,AnalyseDto.class)).collect(Collectors.toList());
    }

    @Override
    public AnalyseDto getAnalyseById(Long id) {
        return iAnalyseRepository.findByIdAndDeletedFalse(id).map(analyse -> modelMapper.map(analyse,AnalyseDto.class)).orElse(null);
    }

    @Override
    public AnalyseDto updateAnalyse(AnalyseDto analyseDto, Long id) {
        Analyse existingAnalyse = iAnalyseRepository.findById(id).orElse(null);
        System.out.println(id);
        existingAnalyse.setStatusAnalyse(analyseDto.getStatusAnalyse());
        existingAnalyse.setDateDebutAnalyse(analyseDto.getDateDebutAnalyse());
        existingAnalyse.setDateFinAnalyse(analyseDto.getDateFinAnalyse());
        existingAnalyse.setCommentaires(analyseDto.getCommentaires());
        Analyse updateAnalyse = iAnalyseRepository.save(existingAnalyse);
        updateAnalyse.setId(id);
        return modelMapper.map(updateAnalyse, AnalyseDto.class);
    }

    @Override
    public void deleteAnalyse(Long id) {
        Analyse analyse = iAnalyseRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (analyse != null) {
            analyse.setDeleted(true);
            iAnalyseRepository.save(analyse);
        }
    }

    @Override
    public PlanificationDto planifierAnalyse(PlanificationDto planificationDTO) {
        Planification planification = iPlanificationRepository.save(modelMapper.map(planificationDTO , Planification.class));
        return modelMapper.map(planification, PlanificationDto.class);
    }

    @Override
    public List<Object[]> printResultAnalyse(Long id) {
        return iAnalyseRepository.getAnalysisReport(id);
    }

    @Override
    public AnalyseDto createAnalyseForEchantillon(Echantillon echantillon) {
        Analyse analyse= new Analyse();
        analyse.setEchantillon(echantillon);
        analyse.setNom(echantillon.getNomAnalyse());
        Analyse savedAnalyse = iAnalyseRepository.save(analyse);
        return modelMapper.map(savedAnalyse, AnalyseDto.class);
    }


}
