package com.example.labxspringboot.dto;

import com.example.labxspringboot.entity.Norme;
import com.example.labxspringboot.entity.Reactif;
import com.example.labxspringboot.entity.TestAnalyse;
import com.example.labxspringboot.entity.TypeAnalyse;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Norme}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class NormeDto implements Serializable {
    Long id;
    String description;
    double min;
    double max;
    String unite;
//    @ToString.Exclude
    List<TestAnalyse> testAnalyses;
    boolean deleted;
}