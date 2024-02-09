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
@AllArgsConstructor
@NoArgsConstructor
public class TypeAnalyse {
    @Id
    @GeneratedValue
    private Long id ;
    private String nom;
    @ToString.Exclude
    @OneToMany(mappedBy = "typeAnalyse" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<TestAnalyse> testAnalyses;
    @ManyToOne
    private Analyse analyse;
    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;

}
