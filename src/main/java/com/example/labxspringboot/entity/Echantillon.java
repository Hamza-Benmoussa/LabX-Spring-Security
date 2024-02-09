package com.example.labxspringboot.entity;

import com.example.labxspringboot.entity.enume.StatusAnalyse;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "echantillon")

public class Echantillon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomAnalyse;

    @ManyToOne
    private Patient patient;

    @ToString.Exclude
    @OneToMany(mappedBy = "echantillon" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore  // Add this annotation to break the loop
    private List<Analyse> analyses;

    @ManyToOne
    private Utilisateur utilisateurPreleveur;

    @OneToMany(mappedBy = "echantillon" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<EchantillonMaterial> echantillonMaterials;

    private String datePrelevement;

    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;

}

