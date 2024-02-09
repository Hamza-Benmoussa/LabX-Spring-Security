package com.example.labxspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "norme")
public class Norme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private double min;

    private double max;

    private String unite;

    @Column(name="is_deleted" ,nullable = false)
    private boolean deleted;

    @OneToMany(mappedBy = "norme" ,cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonIgnore  // Add this annotation to break the loop
    @Fetch(FetchMode.SUBSELECT)
    private List<TestAnalyse> testAnalyse;
}
