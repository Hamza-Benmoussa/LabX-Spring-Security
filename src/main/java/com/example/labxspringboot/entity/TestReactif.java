package com.example.labxspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestReactif {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    TestAnalyse testAnalyse;
    @ManyToOne
    Reactif reactif;
    private int quantity;
    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;
}
