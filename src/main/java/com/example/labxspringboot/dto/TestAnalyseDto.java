package com.example.labxspringboot.dto;

import com.example.labxspringboot.entity.*;
import com.example.labxspringboot.entity.enume.StatusResultat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link TestAnalyse}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestAnalyseDto implements Serializable {
    Long id;
    String description;
    Utilisateur utilisateurTechnicien;
    StatusResultat statusResultat;
    float resultatNmbr;
    TypeAnalyse typeAnalyse;
    List<TestReactif> testReactifs;
    Norme norme;
    boolean deleted;

}