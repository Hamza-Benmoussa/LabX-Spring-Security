package com.example.labxspringboot.dto;

import com.example.labxspringboot.dto.TestAnalyseDto;
import com.example.labxspringboot.entity.Analyse;
import com.example.labxspringboot.entity.TestAnalyse;
import com.example.labxspringboot.entity.TypeAnalyse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link TypeAnalyse}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAnalyseDto implements Serializable {
    Long id;
    String nom;
    List<TestAnalyse> testAnalyses;
    Analyse analyse;

}