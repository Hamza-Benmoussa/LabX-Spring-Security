package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.*;
import com.example.labxspringboot.entity.*;
import com.example.labxspringboot.repository.INormeRepository;
import com.example.labxspringboot.repository.ITestAnalyseRepository;
import com.example.labxspringboot.repository.ITypeAnalyseRepository;
import com.example.labxspringboot.service.ITypeAnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeAnalyseServiceImpl implements ITypeAnalyseService {

    @Autowired
    private ITypeAnalyseRepository iTypeAnalyseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public TypeAnalyseDto saveTypeAnalyse(TypeAnalyseDto typeAnalyseDto) {
        TypeAnalyse typeAnalyse = modelMapper.map(typeAnalyseDto, TypeAnalyse.class);
        TypeAnalyse savedTypeAnalyse = iTypeAnalyseRepository.save(typeAnalyse);
        return modelMapper.map(savedTypeAnalyse, TypeAnalyseDto.class);
    }

    @Override
    public List<TypeAnalyseDto> getTypeAnalyses() {
        List<TypeAnalyse> typeAnalyses = iTypeAnalyseRepository.findByDeletedFalse();
        return typeAnalyses.stream()
                .map(typeAnalyse -> modelMapper.map(typeAnalyse, TypeAnalyseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TypeAnalyseDto getTypeAnalyseById(Long id) {
        return iTypeAnalyseRepository.findByIdAndDeletedFalse(id)
                .map(typeAnalyse -> modelMapper.map(typeAnalyse, TypeAnalyseDto.class))
                .orElse(null);
    }

    @Override
    public TypeAnalyseDto updateTypeAnalyse(TypeAnalyseDto typeAnalyseDto, Long id) {
        TypeAnalyse existingTypeAnalyse = iTypeAnalyseRepository.findById(id).orElse(null);
        if (existingTypeAnalyse != null) {
            modelMapper.map(typeAnalyseDto, existingTypeAnalyse);
            existingTypeAnalyse.setId(id);
            TypeAnalyse updatedTypeAnalyse = iTypeAnalyseRepository.save(existingTypeAnalyse);
            return modelMapper.map(updatedTypeAnalyse, TypeAnalyseDto.class);
        }
        return null;
    }

    @Override
    public void deleteTypeAnalyse(Long id) {
        TypeAnalyse typeAnalyse = iTypeAnalyseRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (typeAnalyse != null) {
            typeAnalyse.setDeleted(true);
            iTypeAnalyseRepository.save(typeAnalyse);
        }

    }


//    @Override
//    public void saveAnalyse(AnalyseDto analyseDto) {
//     TypeAnalyse typeAnalyseDto = analyseDto.getTypeAnalyse();
//        List<Norme> normeList = typeAnalyseDto.getNormes();
//        for (Norme norme : normeList){
//            TestAnalyse testAnalyse = new TestAnalyse();
//            testAnalyse.setUtilisateurRespo(analyseDto.getUtilisateurRespo());
//            testAnalyse.setNorme(norme);
//            testAnalyse.setAnalyse(modelMapper.map(analyseDto , Analyse.class));
//            testAnalyse.setDescription(testAnalyse.toString());
////            testAnalyse.setStatusResultat(testAnalyseService.generateStatusTest(testAnalyse));
//            testAnalyseRepository.save(testAnalyse);
//        }
//    }
//
//    @Override
//    public TestAnalyseDto getTypeAnalyseById(Long id) {
//        return iTypeAnalyseRepository.findByIdAndDeletedFalse(id)
//                .map(typeAnalyse -> modelMapper.map(typeAnalyse, TestAnalyseDto.class))
//                .orElse(null);
//    }
//
//    @Override
//    public TypeAnalyse getTypeAnalyseByName(String typeAnalyseName) {
//        return iTypeAnalyseRepository.getTypeAnalyseByName(typeAnalyseName);
//    }

}
