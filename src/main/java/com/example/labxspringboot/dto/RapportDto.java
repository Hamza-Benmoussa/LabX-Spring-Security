package com.example.labxspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RapportDto implements Serializable {
    private String nomanalyse;
    private String nom;
    private String prenom;
    private String numero;
    private String adresse;
    private String datenaissancepatient;
    private String nomtypeanalyse;
    private String nomtest;
    private String resultat;
    private String libellenorme;
    private Double maxvaluenorme;
    private Double minvaluenorme;
    private String unitenorme;
}
