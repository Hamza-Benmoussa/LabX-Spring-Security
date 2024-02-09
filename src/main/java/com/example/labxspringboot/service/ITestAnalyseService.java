package com.example.labxspringboot.service;

import com.example.labxspringboot.dto.TestAnalyseDto;
import com.example.labxspringboot.entity.TestAnalyse;
import com.example.labxspringboot.entity.enume.StatusResultat;

import java.util.List;

public interface ITestAnalyseService {
    TestAnalyseDto saveTestAnalyse(TestAnalyseDto testAnalyseDto);
    List<TestAnalyseDto> getTestAnalyses();
    TestAnalyseDto getTestAnalyseById(Long id);
    TestAnalyseDto updateTestAnalyse(TestAnalyseDto testAnalyseDto, Long id);
    void deleteTestAnalyse(Long id);
    StatusResultat generateStatusTest(TestAnalyseDto testAnalyseDto);
}
