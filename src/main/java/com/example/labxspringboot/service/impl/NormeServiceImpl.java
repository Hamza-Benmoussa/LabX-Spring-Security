package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.NormeDto;
import com.example.labxspringboot.entity.MaterielEchan;
import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.repository.INormeRepository;
import com.example.labxspringboot.service.INormeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NormeServiceImpl implements INormeService {

    @Autowired
    private  INormeRepository normeRepository;
    @Autowired
    private  ModelMapper modelMapper;


    @Override
    public NormeDto saveNorme(NormeDto normeDto) {
        Norme norme = modelMapper.map(normeDto, Norme.class);
        Norme savedNorme = normeRepository.save(norme);
        return modelMapper.map(savedNorme, NormeDto.class);
    }

    @Override
    public List<NormeDto> getNormes() {
        List<Norme> normes = normeRepository.findByDeletedFalse();
        return normes.stream()
                .map(norme -> modelMapper.map(norme, NormeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NormeDto getNormeById(Long id) {
        return normeRepository.findByIdAndDeletedFalse(id)
                .map(norme -> modelMapper.map(norme, NormeDto.class))
                .orElse(null);
    }

    @Override
    public NormeDto updateNorme(NormeDto normeDto, Long id) {
        Norme existingNorme = normeRepository.findByIdAndDeletedFalse(id).orElse(null);
        existingNorme.setDescription(normeDto.getDescription());
        existingNorme.setMax(normeDto.getMax());
        existingNorme.setMin(normeDto.getMin());
        existingNorme.setUnite(normeDto.getUnite());

        Norme updateNorme = normeRepository.save(existingNorme);

        updateNorme.setId(id);

        return modelMapper.map(updateNorme, NormeDto.class);
    }

    @Override
    public void deleteNorme(Long id) {
        Norme norme = normeRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (norme != null) {
            norme.setDeleted(true);
            normeRepository.save(norme);
        }
    }
}
