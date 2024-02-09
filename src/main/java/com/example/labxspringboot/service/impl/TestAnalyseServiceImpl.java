package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.TestAnalyseDto;
import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Reactif;
import com.example.labxspringboot.entity.TestAnalyse;
import com.example.labxspringboot.entity.enume.StatusResultat;
import com.example.labxspringboot.repository.ITestAnalyseRepository;
import com.example.labxspringboot.service.ITestAnalyseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestAnalyseServiceImpl implements ITestAnalyseService {

    @Autowired
    private ITestAnalyseRepository testRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TestAnalyseDto saveTestAnalyse(TestAnalyseDto testAnalyseDto) {
        testAnalyseDto.setStatusResultat(generateStatusTest(testAnalyseDto));
        TestAnalyse savedTestAnalyse = testRepository.save(modelMapper.map(testAnalyseDto, TestAnalyse.class));
        return modelMapper.map(savedTestAnalyse, TestAnalyseDto.class);
    }

    @Override
    public List<TestAnalyseDto> getTestAnalyses() {
        List<TestAnalyse> testAnalyses = testRepository.findAll();
        return testAnalyses.stream()
                .map(testAnalyse -> modelMapper.map(testAnalyse, TestAnalyseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TestAnalyseDto getTestAnalyseById(Long id) {
        return testRepository.findById(id)
                .map(testAnalyse -> modelMapper.map(testAnalyse, TestAnalyseDto.class))
                .orElse(null);
    }

    @Override
    public TestAnalyseDto updateTestAnalyse(TestAnalyseDto testAnalyseDto, Long id) {
        testAnalyseDto.setStatusResultat(generateStatusTest(testAnalyseDto));
        TestAnalyse existingTestAnalyse = testRepository.findById(id).orElse(null);
        if (existingTestAnalyse != null) {
            modelMapper.map(testAnalyseDto, existingTestAnalyse);
            existingTestAnalyse.setId(id);
            TestAnalyse updatedTestAnalyse = testRepository.save(existingTestAnalyse);
            return modelMapper.map(updatedTestAnalyse, TestAnalyseDto.class);
        }
        return null;
    }

    @Override
    public void deleteTestAnalyse(Long id) {
        TestAnalyse testAnalyse = testRepository.findByIdAndDeletedFalse(id).orElse(null);
        if (testAnalyse != null) {
            testAnalyse.setDeleted(true);
            testRepository.save(testAnalyse);
        }
    }

    @Override
    public StatusResultat generateStatusTest(TestAnalyseDto testAnalyseDto) {
        Norme norme = modelMapper.map(testAnalyseDto.getNorme(), Norme.class);
        if (testAnalyseDto.getResultatNmbr() >= norme.getMin() && testAnalyseDto.getResultatNmbr() <= norme.getMax()) {
            return StatusResultat.NORMAL;
        } else {
            return StatusResultat.ANORMAL;
        }
    }
}
