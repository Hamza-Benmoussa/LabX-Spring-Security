package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.MaterielEchanDto;
import com.example.labxspringboot.entity.MaterielEchan;
import com.example.labxspringboot.repository.IMaterialRepository;
import com.example.labxspringboot.service.IMaterialEchanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialEchanServiceImpl implements IMaterialEchanService {

    private final IMaterialRepository materialRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MaterialEchanServiceImpl(IMaterialRepository materialRepository, ModelMapper modelMapper) {
        this.materialRepository = materialRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MaterielEchanDto saveMaterialEchan(MaterielEchanDto materielEchanDto) {
        MaterielEchan materielEchan = modelMapper.map(materielEchanDto, MaterielEchan.class);
        MaterielEchan savedMaterialEchan = materialRepository.save(materielEchan);
        return modelMapper.map(savedMaterialEchan, MaterielEchanDto.class);
    }

    @Override
    public List<MaterielEchanDto> getMaterialEchans() {
        List<MaterielEchan> materialEchans = materialRepository.findByDeletedFalse();
        return materialEchans.stream()
                .map(material -> modelMapper.map(material, MaterielEchanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MaterielEchanDto getMaterialEchanById(Long id) {
        return materialRepository.findByIdAndDeletedFalse(id)
                .map(material -> modelMapper.map(material, MaterielEchanDto.class))
                .orElse(null);
    }

    @Override
    public MaterielEchanDto updateMaterialEchan(MaterielEchanDto materielEchanDto, Long id) {
        MaterielEchan existingMaterialEchan = materialRepository.findByIdAndDeletedFalse(id).orElse(null);
        existingMaterialEchan.setDateExpirationEchan(materielEchanDto.getDateExpirationEchan());
        existingMaterialEchan.setFournisseurNom(materielEchanDto.getFournisseurNom());
        existingMaterialEchan.setNomechan(materielEchanDto.getNomechan());
        existingMaterialEchan.setQuantiteStockEhcna(materielEchanDto.getQuantiteStockEhcna());
        MaterielEchan updateMaterialEchan = materialRepository.save(existingMaterialEchan);
        updateMaterialEchan.setId(id);
        return modelMapper.map(updateMaterialEchan,MaterielEchanDto.class);
    }

    @Override
    public void deleteMaterialEchan(Long id) {
        MaterielEchan materialEchan = materialRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (materialEchan != null) {
            materialEchan.setDeleted(true);
            materialRepository.save(materialEchan);
        }
    }

}
