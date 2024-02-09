package com.example.labxspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.labxspringboot.entity.Reactif}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReactifDto implements Serializable {
    Long id;
    String nom;
    String description;
    int quantiteStock;
    String dateExpiration;
    String fournisseurNom;
    boolean deleted;
}