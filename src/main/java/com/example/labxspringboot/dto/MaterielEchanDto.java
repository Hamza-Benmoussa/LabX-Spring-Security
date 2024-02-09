package com.example.labxspringboot.dto;

import com.example.labxspringboot.entity.EchantillonMaterial;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.labxspringboot.entity.MaterielEchan}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MaterielEchanDto implements Serializable {
    Long id;
    String nomechan;
    int quantiteStockEhcna;
    String dateExpirationEchan;
    String fournisseurNom;
    List<EchantillonMaterial> echantillonMaterials;
    boolean deleted;
}