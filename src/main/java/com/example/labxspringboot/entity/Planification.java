package com.example.labxspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planification {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToOne
    private Analyse analyse;
    @Column(name="is_deleted" , nullable = false)
    private boolean deleted;
}
