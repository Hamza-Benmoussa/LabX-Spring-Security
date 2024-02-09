package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.NormeDto;
import com.example.labxspringboot.dto.ReactifDto;
import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Reactif;
import com.example.labxspringboot.entity.TypeAnalyse;
import com.example.labxspringboot.service.INormeService;
import com.example.labxspringboot.service.IReactifService;
import com.example.labxspringboot.service.ITypeAnalyseService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.checkerframework.checker.units.UnitsTools.min;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class NormeServiceImplTest {
    @Autowired
    private INormeService iNormeService;
    @Rollback(value = false)
    @ParameterizedTest
    @CsvFileSource(resources = "/listTest.csv", numLinesToSkip = 1)
    void saveNorme(String description,double min,double max,String unite) {
        NormeDto normeDto = new NormeDto();
        normeDto.setDescription(description);
        normeDto.setMin(min);
        normeDto.setMax(max);
        normeDto.setUnite(unite);
        normeDto.setDeleted(false);

        iNormeService.saveNorme(normeDto);

    }
}