package com.example.labxspringboot.dto;

import com.example.labxspringboot.entity.Utilisateur;
import com.example.labxspringboot.entity.enume.RoleUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link Utilisateur}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto implements Serializable {

    Long id;

    @NotNull
    @Email
    String Email;
    @NotNull
    String nom;
    //@JsonIgnore
    @NotBlank
    String motDePasse;
    @NotNull
    RoleUser role;
    boolean deleted;
}