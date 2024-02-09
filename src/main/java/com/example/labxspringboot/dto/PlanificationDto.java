package com.example.labxspringboot.dto;

import com.example.labxspringboot.entity.Analyse;
import com.example.labxspringboot.entity.Echantillon;
import com.example.labxspringboot.entity.Planification;
import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.entity.enume.RoleUser;
import com.example.labxspringboot.entity.enume.StatusAnalyse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Planification}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanificationDto implements Serializable {
    Long id;
    Utilisateur utilisateur;
    Analyse analyse;
    boolean deleted;
}