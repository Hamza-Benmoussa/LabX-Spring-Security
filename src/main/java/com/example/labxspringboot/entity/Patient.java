package com.example.labxspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String dateNaissance;

    private String sexe;

    private String adresse;

    private String numeroTelephone;

    public Patient(String nom, String prenom, String dateNaissance, String sexe, String adresse, String numeroTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
    }

    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;
    @ToString.Exclude
    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL  ,fetch = FetchType.EAGER)
//    @ElementCollection(fetch = FetchType.LAZY)
    @JsonIgnore  // Add this annotation to break the loop
    @Fetch(FetchMode.SUBSELECT)
    private List<Echantillon> historiqueEchantillon;


}
