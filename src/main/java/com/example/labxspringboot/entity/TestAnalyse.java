package com.example.labxspringboot.entity;

import com.example.labxspringboot.entity.enume.StatusResultat;
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
@Table(name = "test")
public class TestAnalyse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    private Utilisateur utilisateurTechnicien;

    @Enumerated(EnumType.STRING)
    private StatusResultat statusResultat;

    private float resultatNmbr;

    @ManyToOne
    private TypeAnalyse typeAnalyse;


    @ToString.Exclude
    @OneToMany(mappedBy = "testAnalyse" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<TestReactif> testReactifs;
    @ManyToOne
    private Norme norme;

    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;
}
