package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.EchantillonDto;
import com.example.labxspringboot.entity.Echantillon;
import com.example.labxspringboot.entity.EchantillonMaterial;

public interface IEchantillonMaterialService {
    EchantillonMaterial addEchantillon(EchantillonMaterial echantillonMaterial);
}
