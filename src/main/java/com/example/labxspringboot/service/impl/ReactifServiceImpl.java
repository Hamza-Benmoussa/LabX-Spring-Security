package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.ReactifDto;
import com.example.labxspringboot.entity.Reactif;
import com.example.labxspringboot.repository.IReactifRepository;
import com.example.labxspringboot.service.IReactifService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactifServiceImpl implements IReactifService {


    @Autowired
    private IReactifRepository reactifRepository;
    @Autowired
    private  ModelMapper modelMapper;




    public ReactifDto saveReactif(ReactifDto reactifDto) {
        Reactif reactif = modelMapper.map(reactifDto, Reactif.class);
        Reactif savedReactif = reactifRepository.save(reactif);
        return modelMapper.map(savedReactif, ReactifDto.class);
    }

    public List<ReactifDto> getReactifs() {
        List<Reactif> reactifs = reactifRepository.findByDeletedFalse();
        return reactifs.stream()
                .map(reactif -> modelMapper.map(reactif, ReactifDto.class))
                .collect(Collectors.toList());
    }

    public ReactifDto getReactifById(Long id) {
        return reactifRepository.findByIdAndDeletedFalse(id)
                .map(reactif -> modelMapper.map(reactif, ReactifDto.class))
                .orElse(null);
    }

    public ReactifDto updateReactif(ReactifDto reactifDto, Long id) {
        Reactif existingReactif = reactifRepository.findById(id).orElse(null);
        if (existingReactif != null) {
            modelMapper.map(reactifDto, existingReactif);
            existingReactif.setId(id);
            Reactif savedReactif = reactifRepository.save(existingReactif);
            return modelMapper.map(savedReactif, ReactifDto.class);
        }
        return null;
    }

    public void deleteReactif(Long id) {
        Reactif reactif = reactifRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (reactif != null) {
            reactif.setDeleted(true);
            reactifRepository.save(reactif);
        }
    }


}
