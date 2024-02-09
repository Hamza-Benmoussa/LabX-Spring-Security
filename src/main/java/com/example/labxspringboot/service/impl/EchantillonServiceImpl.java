package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.AnalyseDto;
import com.example.labxspringboot.dto.EchantillonDto;
import com.example.labxspringboot.entity.Analyse;
import com.example.labxspringboot.entity.Echantillon;
import com.example.labxspringboot.repository.IEchantillonRepository;
import com.example.labxspringboot.service.IAnalyseService;
import com.example.labxspringboot.service.IEchantillonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class EchantillonServiceImpl implements IEchantillonService {

    @Autowired
    private IEchantillonRepository echantillonRepository;

    @Autowired
    private IAnalyseService iAnalyseService;

    private final ModelMapper modelMapper;

    @Override
    public EchantillonDto saveEchantillon(EchantillonDto echantillonDto) {
        // Map the DTO to an Echantillon entity
        log.info("echantillonDto {}",echantillonDto);
        Echantillon echantillon = modelMapper.map(echantillonDto, Echantillon.class);
        log.info("echantillonDto {}",echantillonDto);
        // Save the Echantillon entity
        Echantillon savedEchantillon = echantillonRepository.save(echantillon);
        // Create an Analyse entity and associate it with the saved Echantillon
//        AnalyseDto analyseDto = new AnalyseDto();
//        analyseDto.setNom(echantillonDto.getNomAnalyse());
        AnalyseDto savedAnalyse = iAnalyseService.createAnalyseForEchantillon(savedEchantillon);
        System.out.println(savedEchantillon);
        // Update the Echantillon with the saved Analyse
//        savedEchantillon.getAnalyses().add(savedAnalyse);

        // Save the updated Echantillon
        Echantillon updatedEchantillon = echantillonRepository.save(savedEchantillon);

        // Map the updated Echantillon back to DTO and return
        return modelMapper.map(updatedEchantillon, EchantillonDto.class);
    }


    @Override
    public List<EchantillonDto> getEchantillons() {
        List<Echantillon> echantillons = echantillonRepository.findByDeletedFalse();
        return echantillons.stream().map(echantillon -> modelMapper.map(echantillon, EchantillonDto.class)).collect(Collectors.toList());
    }

    @Override
    public EchantillonDto getEchantillonById(Long id) {
        return echantillonRepository.findByIdAndDeletedFalse(id)
                .map(echantillon -> modelMapper.map(echantillon, EchantillonDto.class))
                .orElse(null);
    }

    @Override
    public EchantillonDto updateEchantillon(EchantillonDto echantillonDto, Long id) {
        Echantillon existingEchantillon = echantillonRepository.findById(id).orElse(null);
        if (existingEchantillon != null) {
            modelMapper.map(echantillonDto, existingEchantillon);
            existingEchantillon.setId(id);
            Echantillon savedEchantillon = echantillonRepository.save(existingEchantillon);
            return modelMapper.map(savedEchantillon, EchantillonDto.class);
        }
        return null;
    }

    @Override
    public void deleteEchantillon(Long id) {
        Echantillon echantillon = echantillonRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (echantillon != null) {
            echantillon.setDeleted(true);
            echantillonRepository.save(echantillon);
        }
    }
}
