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
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "analyses")
public class Analyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Echantillon echantillon;

    private String nom;

    @ManyToOne
    private Utilisateur utilisateurTechnicien;

    private String dateDebutAnalyse;

    private String dateFinAnalyse;

    @Enumerated(EnumType.STRING)
    private StatusAnalyse statusAnalyse;

    private String commentaires;

    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;
    @ToString.Exclude

    @OneToMany(mappedBy = "analyse" ,cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<TypeAnalyse> typeAnalyses;

}
