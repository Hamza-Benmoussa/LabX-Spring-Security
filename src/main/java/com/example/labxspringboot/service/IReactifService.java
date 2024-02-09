package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.ReactifDto;
import com.example.labxspringboot.entity.Reactif;

import java.util.List;

public interface IReactifService {

    ReactifDto saveReactif(ReactifDto reactifDto);

    List<ReactifDto> getReactifs();

    ReactifDto getReactifById(Long id);

    ReactifDto updateReactif(ReactifDto reactifDto, Long id);

    void deleteReactif(Long id);

}
