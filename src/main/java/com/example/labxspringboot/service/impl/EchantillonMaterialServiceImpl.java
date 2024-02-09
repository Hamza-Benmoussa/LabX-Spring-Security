package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.entity.EchantillonMaterial;
import com.example.labxspringboot.repository.IEchantillonMaterialRepository;
import com.example.labxspringboot.service.IEchantillonMaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EchantillonMaterialServiceImpl implements IEchantillonMaterialService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEchantillonMaterialRepository iEchantillonMaterialRepository;


    @Override
    public EchantillonMaterial addEchantillon(EchantillonMaterial echantillonMaterial) {
        return iEchantillonMaterialRepository.save(echantillonMaterial);
    }
    }

