package com.example.labxspringboot.entity;

import com.example.labxspringboot.entity.enume.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;

    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private RoleUser role;


    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;

    public Utilisateur(String nom,String email, String motDePasse, RoleUser role) {
        this.nom=nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }


    //    @OneToMany(mappedBy = "utilisateur")
//    private List<RapportStatis> generateursRapports;


}
